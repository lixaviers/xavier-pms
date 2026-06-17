package com.xavier.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OperateLogDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户IP
     */
    private String userIp;

    /**
     * 操作名
     */
    private String operateName;

    /**
     * 请求方法名
     */
    private String requestMethod;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 浏览器UserAgent
     */
    private String userAgent;

    /**
     * Java方法名
     */
    private String javaMethod;

    /**
     * Java方法的参数
     */
    private String javaMethodArgs;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 链路追踪id
     */
    private String traceId;

    private String token;

    /**
     * 执行时长，单位：毫秒
     */
    private Integer duration;

    /**
     * 结果码
     */
    private Integer resultCode;

    /**
     * 结果提示
     */
    private String resultMsg;

    /**
     * 结果数据
     */
    private String resultData;

}
