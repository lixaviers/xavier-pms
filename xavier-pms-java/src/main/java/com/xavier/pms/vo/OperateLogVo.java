package com.xavier.pms.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xavier.pms.utils.LongJsonDeserializer;
import com.xavier.pms.utils.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("操作日志出参")
public class OperateLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty("用户IP")
    private String userIp;

    @ApiModelProperty("操作名")
    private String operateName;

    @ApiModelProperty("请求方法名")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("浏览器UserAgent")
    private String userAgent;

    @ApiModelProperty("Java方法名")
    private String javaMethod;

    @ApiModelProperty("Java方法的参数")
    private String javaMethodArgs;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("链路追踪id")
    private String traceId;

    @ApiModelProperty("执行时长，单位：毫秒")
    private Integer duration;

    @ApiModelProperty("结果码")
    private Integer resultCode;

    @ApiModelProperty("结果提示")
    private String resultMsg;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
