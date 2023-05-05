package com.xavier.pms.constants;

public interface Constant {

    /**
     * 图片验证码key
     */
    String CAPTCHA_KEY = "xavier-captcha-";

    /**
     * header key
     */
    String HEADER_KEY = "Authorization";

    /**
     * 默认密码
     */
    String DEFAULT_USER_PWD = "qweasd123";


    /**
     * 菜单类型-目录
     */
    String MENU_DIRECTORY = "M";
    /**
     * 菜单类型-菜单
     */
    String MENU_MENU = "C";
    /**
     * 菜单类型-接口
     */
    String MENU_INTERFACE = "I";

    /**
     * 审批类型-提交
     */
    String APPROVAL_TYPE_START = "start";
    /**
     * 审批类型-审批
     */
    String APPROVAL_TYPE_APPROVAL = "approval";
    /**
     * 审批类型-办理
     */
    String APPROVAL_TYPE_HANDLER = "handler";
    /**
     * 审批类型-抄送
     */
    String APPROVAL_TYPE_CC = "cc";
    /**
     * 审批类型-结束
     */
    String APPROVAL_TYPE_END = "end";


}
