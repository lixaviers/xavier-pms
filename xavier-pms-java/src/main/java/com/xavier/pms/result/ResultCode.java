package com.xavier.pms.result;

public enum ResultCode {

    /**
     * 操作成功标识
     */
    SUCCESS(200, "Success"),

    C401(401, "没有此操作权限"),
    C403(403, "访问受限"),
    C404(404, "资源未找到"),
    C405(405, "请求方法错误"),
    C400(400, "参数列表错误"),
    C415(415, "不支持的数据（媒体类型）"),
    C429(429, "请求过多被限制"),

    SYS_ERROR(500, "当前网络不稳定，请稍后重试"),
    C501(501, "用户未登录"),
    C502(502, "登录失效，请重新登录"),
    C503(503, "用户名和密码不匹配"),
    C504(504, "您的账号已经被禁用，请联系管理员"),

    //----------------------------------------
    // 通用异常
    // 区间：700-799
    //----------------------------------------
    COMMON_PARAM_NULL(700, "必要参数[%s]为空"),
    COMMON_PARAM_INVALID(701, "%s无效"),
    DATA_NOT_EXIST(702, "%s不存在"),
    DATA_EXIST(703, "%s已存在"),

    /**
     * 公用错误
     */
    COMMON_MESSAGE(1000, "%s"),

    ;


    public final int code;
    public final String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
