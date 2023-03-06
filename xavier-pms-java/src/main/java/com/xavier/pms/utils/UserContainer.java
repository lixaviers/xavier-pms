package com.xavier.pms.utils;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.xavier.pms.vo.UserInfoVo;

/**
 * 用户容器
 */
public class UserContainer {

    /**
     * 用户信息线程
     */
    public static final TransmittableThreadLocal<UserInfoVo> USER_INFO_VO_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 获取用户信息
     */
    public static UserInfoVo get() {
        return USER_INFO_VO_THREAD_LOCAL.get();
    }

    /**
     * 设置用户信息
     *
     * @param userInfoVO
     */
    public static void set(UserInfoVo userInfoVO) {
        USER_INFO_VO_THREAD_LOCAL.set(userInfoVO);
    }

    /**
     * 移除用户信息
     */
    public static void remove() {
        USER_INFO_VO_THREAD_LOCAL.remove();
    }


}
