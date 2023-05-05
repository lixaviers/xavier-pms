package com.xavier.pms.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value = "Rest result", description = "请求结果")
@Getter
@Setter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3696898674758052311L;

    @ApiModelProperty(value = "结果代码")
    private Integer code = ResultCode.SUCCESS.code;
    @ApiModelProperty(value = "错误说明")
    private String message = "";
    @ApiModelProperty(value = "结果对象")
    private T data;
    @ApiModelProperty(value = "操作标识")
    private Boolean success = Boolean.TRUE;
    @ApiModelProperty(value = "当前时间戳")
    private Long timestamp;

    public Result(int code, String message, boolean success, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setSuccess(success);
        this.setData(data);
    }

    public Result(int code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setSuccess(code == 200);
        this.setData(data);
    }

    public Result() {
    }

    /**
     * 构建返回对象实例
     *
     * @param resultCode
     * @param success
     * @param value
     * @return
     */
    public static <T> Result<T> ok(ResultCode resultCode, boolean success, T value) {
        return new Result<T>(resultCode.code, resultCode.desc, success, value);
    }

    /**
     * 构建成功对象实例
     *
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<T>(ResultCode.SUCCESS.code,
                ResultCode.SUCCESS.desc, true, null);
    }

    /**
     * 构建成功对象实例
     *
     * @return
     */
    public static <T> Result<T> ok(T value) {
        return new Result<T>(ResultCode.SUCCESS.code,
                ResultCode.SUCCESS.desc, true, value);
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<T>(ResultCode.SUCCESS.code, message, true, data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(ResultCode.COMMON_MESSAGE.code, message, false, null);
    }

    /**
     * 构建错误对象
     *
     * @param errorCode
     * @return
     */

    public static <T> Result<T> error(ResultCode errorCode) {
        return new Result<T>(errorCode.code, errorCode.desc, false, null);
    }

    public static <T> Result<T> error(int code, String message, Object... args) {
        Result<T> r = new Result<T>(code, "", false, null);
        r.setFormatMessage(message, args);
        return r;
    }

    public static <T> Result<T> error(ResultCode resultCode, Object... args) {
        Result<T> r = new Result<T>(resultCode.getCode(), "", false, null);
        r.setFormatMessage(resultCode.getDesc(), args);

        return r;
    }

    public void setFormatMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            this.setMessage(message);
        } else {
            this.setMessage(String.format(message, args));
        }
    }

    public Long getTimestamp() {
        return timestamp == null ? System.currentTimeMillis() : timestamp;
    }

}
