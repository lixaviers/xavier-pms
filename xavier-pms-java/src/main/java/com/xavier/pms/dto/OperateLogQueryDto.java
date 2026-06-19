package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "操作日志查询入参")
public class OperateLogQueryDto extends QueryDto {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户IP")
    private String userIp;

    @Schema(description = "操作名")
    private String operateName;

    @Schema(description = "请求方法")
    private String requestMethod;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "链路追踪ID")
    private String traceId;

    @Schema(description = "结果码")
    private Integer resultCode;

    @Schema(description = "创建时间起")
    private LocalDateTime createTimeFrom;

    @Schema(description = "创建时间止")
    private LocalDateTime createTimeTo;

}
