package com.xavier.pms.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.model.User;
import com.xavier.pms.result.Result;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.utils.ServletUtil;
import com.xavier.pms.utils.UserContainer;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户拦截器
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = request.getHeader(Constant.HEADER_KEY);
            if (StrUtil.isBlank(token)) {
                ServletUtil.renderString(response, JSON.toJSONString(Result.error(ResultCode.C501)));
                return false;
            }
            User user = userService.getByToken(token);
            if (Objects.isNull(user) || user.getExpirationTime().isBefore(LocalDateTime.now())) {
                ServletUtil.renderString(response, JSON.toJSONString(Result.error(ResultCode.C502)));
                return false;
            }
            UserInfoVo userInfoVO = BeanUtil.beanCopy(user, UserInfoVo.class);
            userInfoVO.setToken(token);
            UserContainer.set(userInfoVO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ServletUtil.renderString(response, JSON.toJSONString(Result.error(ResultCode.SYS_ERROR)));
            return false;
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContainer.remove();
    }


}
