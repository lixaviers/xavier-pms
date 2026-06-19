package com.xavier.pms.controller;

import com.xavier.pms.utils.ServletUtil;
import com.xavier.pms.utils.UserContainer;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Slf4j
public class CommonController {

    public HttpServletRequest getRequest() {
        return ServletUtil.getRequest();
    }

    public HttpServletResponse getResponse() {
        return ServletUtil.getResponse();
    }

    public HttpSession getSession() {
        return ServletUtil.getSession();
    }

    protected UserInfoVo getLoginUser() {
        return UserContainer.get();
    }

}
