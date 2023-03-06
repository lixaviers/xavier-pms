package com.xavier.pms.exception;

import com.xavier.pms.result.ResultCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static Map<Integer, String> SERVICE_EXCEPTION_MAP = null;
    /**
     * 异常错误代码
     */
    private Integer code;
    /**
     * 异常错误信息
     */
    private String message;

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(Integer code, String message, Object... args) {
        super(message);
        this.code = code;
        if (args == null || args.length == 0) {
            this.message = message;
        } else {
            this.message = String.format(message, args);
        }
    }

    public ServiceException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getDesc());
    }

    public ServiceException(ResultCode resultCode, Object... args) {
        this(resultCode.getCode(), resultCode.getDesc(), args);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        this.code = ResultCode.COMMON_MESSAGE.getCode();
        this.message = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
