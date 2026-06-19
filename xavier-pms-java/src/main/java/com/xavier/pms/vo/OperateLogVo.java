package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "操作日志出参")
public class OperateLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户IP")
    private String userIp;

    @Schema(description = "操作名")
    private String operateName;

    @Schema(description = "请求方法名")
    private String requestMethod;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "浏览器UserAgent")
    private String userAgent;

    @Schema(description = "Java方法名")
    private String javaMethod;

    @Schema(description = "Java方法的参数")
    private String javaMethodArgs;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "链路追踪id")
    private String traceId;

    @Schema(description = "执行时长，单位：毫秒")
    private Integer duration;

    @Schema(description = "结果码")
    private Integer resultCode;

    @Schema(description = "结果提示")
    private String resultMsg;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
