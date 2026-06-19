package com.xavier.pms.aspects;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.xavier.pms.annotation.SysOperateLog;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dto.OperateLogDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.result.Result;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IOperateLogService;
import com.xavier.pms.utils.ServletUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 拦截使用 @SysOperateLog 注解，如果满足条件，则生成操作日志。
 * 但是，如果声明 @SysOperateLog 注解时，将 enable 属性设置为 false 时，强制不记录。
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class SysOperateLogAspect {

    private final IOperateLogService operateLogService;

    @Around("@annotation(operation)")
    public Object around(ProceedingJoinPoint joinPoint, Operation operation) throws Throwable {
        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();
        // 可能也添加了 @SysOperateLog 注解
        SysOperateLog operateLog = getMethodAnnotation(joinPoint, SysOperateLog.class);
        try {
            // 执行原有方法
            Object result = joinPoint.proceed();
            this.log(joinPoint, operateLog, operation, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            this.log(joinPoint, operateLog, operation, startTime, null, exception);
            throw exception;
        }
    }

    private void log(ProceedingJoinPoint joinPoint, SysOperateLog operateLog, Operation operation, LocalDateTime startTime, Object result, Throwable exception) {
        try {
            // 判断不记录的情况
            if (!isLogEnable(operateLog)) {
                return;
            }
            // 真正记录操作日志
            doLog(joinPoint, operateLog, operation, startTime, result, exception);
        } catch (Throwable ex) {
            log.error("[log][记录操作日志时发生异常，参数是 joinPoint({}) operateLog({}) operation({}) result({}) exception({}) ]", joinPoint, operateLog, operation, result, exception, ex);
        }
    }

    private void doLog(ProceedingJoinPoint joinPoint, SysOperateLog operateLog, Operation operation, LocalDateTime startTime, Object result, Throwable exception) {
        OperateLogDto dto = new OperateLogDto();
        // 补全通用字段
        dto.setTraceId(MDC.get(Constant.LOG_TRACE_ID));
        dto.setStartTime(startTime);
        // 补全请求信息
        fillRequestFields(dto);
        // 补全模块信息
        fillModuleFields(dto, operateLog, operation);
        // 补全方法信息
        fillMethodFields(dto, joinPoint, operateLog, startTime, result, exception);
        // 异步记录日志
        operateLogService.addOperateLog(dto);
    }

    private void fillRequestFields(OperateLogDto dto) {
        // 获得 Request 对象
        HttpServletRequest request = ServletUtil.getRequest();
        if (Objects.isNull(request)) {
            return;
        }
        // 补全请求信息
        dto.setRequestMethod(request.getMethod());
        dto.setRequestUrl(request.getRequestURI());
        dto.setUserIp(ServletUtil.getClientIP(request));
        dto.setUserAgent(ServletUtil.getUserAgent(request));
        dto.setToken(request.getHeader(Constant.HEADER_KEY));
    }

    private void fillModuleFields(OperateLogDto dto, SysOperateLog operateLog, Operation operation) {
        String name = Objects.nonNull(operateLog) ? operateLog.name() : null;
        if (StrUtil.isEmpty(name)) {
            name = operation.summary();
        }
        dto.setOperateName(name);
    }

    private void fillMethodFields(OperateLogDto dto, ProceedingJoinPoint joinPoint, SysOperateLog operateLog, LocalDateTime startTime, Object result, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        dto.setJavaMethod(methodSignature.toString());
        if (Objects.isNull(operateLog) || operateLog.logArgs()) {
            dto.setJavaMethodArgs(obtainMethodArgs(joinPoint));
        }
        if (Objects.isNull(operateLog) || operateLog.logResultData()) {
            dto.setResultData(obtainResultData(result));
        }
        dto.setDuration((int) (LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis()));
        // （正常）处理 resultCode 和 resultMsg 字段
        if (result instanceof Result) {
            Result<?> commonResult = (Result<?>) result;
            dto.setResultCode(commonResult.getCode());
            dto.setResultMsg(commonResult.getMessage());
        } else {
            dto.setResultCode(ResultCode.SUCCESS.getCode());
        }
        // （异常）处理 resultCode 和 resultMsg 字段
        if (exception != null) {
            if (exception instanceof ServiceException) {
                ServiceException serviceException = (ServiceException) exception;
                dto.setResultCode(serviceException.getCode());
                dto.setResultMsg(serviceException.getMessage());
            } else {
                dto.setResultCode(ResultCode.SYS_ERROR.getCode());
                dto.setResultMsg(ExceptionUtil.getRootCauseMessage(exception));
            }
        }
    }

    private boolean isLogEnable(SysOperateLog operateLog) {
        // 有 @SysOperateLog 注解的情况下
        if (Objects.nonNull(operateLog)) {
            return operateLog.enable();
        }
        return true;
    }

    @SuppressWarnings("SameParameterValue")
    private <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(annotationClass);
    }

    private String obtainMethodArgs(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        // 拼接参数
        Map<String, Object> args = MapUtil.newHashMap(argValues.length);
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            if (Objects.nonNull(argValue)) {
                // 被忽略时，标记为 ignore 字符串，避免和 null 混在一起
                args.put(argName, !isIgnoreArgs(argValue) ? argValue : "[ignore]");
            }
        }
        return JSON.toJSONString(args);
    }

    private String obtainResultData(Object result) {
        if (result instanceof Result) {
            result = ((Result<?>) result).getData();
        }
        return Objects.isNull(result) ? "" : JSON.toJSONString(result);
    }

    private static boolean isIgnoreArgs(Object object) {
        Class<?> clazz = object.getClass();
        // 处理数组的情况
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object)).anyMatch(index -> isIgnoreArgs(Array.get(object, index)));
        }
        // 递归，处理数组、Collection、Map 的情况
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream().anyMatch((Predicate<Object>) SysOperateLogAspect::isIgnoreArgs);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return isIgnoreArgs(((Map<?, ?>) object).values());
        }
        // obj
        return object instanceof MultipartFile || object instanceof HttpServletRequest || object instanceof HttpServletResponse || object instanceof BindingResult;
    }

}
