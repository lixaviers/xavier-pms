package com.xavier.pms.config;

import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.result.Result;
import com.xavier.pms.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.Arrays;
import java.util.Objects;


@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String[] supportedMethods = e.getSupportedMethods();
        if (Objects.isNull(supportedMethods)) {
            return Result.error(ResultCode.C405);
        }
        return Result.error(ResultCode.C405, Arrays.toString(supportedMethods));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("参数异常", e);
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), "参数异常");
    }

    @ExceptionHandler(value = {Throwable.class, MethodArgumentNotValidException.class})
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("参数异常", e);
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), defaultMessage);
    }

    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        log.warn("参数异常", e);
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), defaultMessage);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("参数异常", e);
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.warn("参数异常", e);
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result<String> handleMultipartException(MultipartException e) {
        log.warn("参数异常", e);
        return Result.error(ResultCode.COMMON_PARAM_NULL.getCode(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException e) {
        log.warn("业务异常: {}", e.getMessage(), e);
        Result<String> result = Result.error(e.getMessage());
        result.setCode(e.getCode());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("Occur exception!", e);
        return Result.error(ResultCode.SYS_ERROR);
    }

}

