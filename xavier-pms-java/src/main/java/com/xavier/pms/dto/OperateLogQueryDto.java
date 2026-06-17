package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("操作日志查询入参")
public class OperateLogQueryDto extends QueryDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户IP")
    private String userIp;

    @ApiModelProperty("操作名")
    private String operateName;

    @ApiModelProperty("请求方法")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("链路追踪ID")
    private String traceId;

    @ApiModelProperty("结果码")
    private Integer resultCode;

    @ApiModelProperty("创建时间起")
    private LocalDateTime createTimeFrom;

    @ApiModelProperty("创建时间止")
    private LocalDateTime createTimeTo;

}
