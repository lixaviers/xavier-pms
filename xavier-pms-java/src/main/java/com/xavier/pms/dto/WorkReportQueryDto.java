package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 工作汇报查询入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "工作汇报查询入参")
public class WorkReportQueryDto extends QueryDto {

    private static final long serialVersionUID = 1L;

    @Schema(description = "汇报标题")
    private String title;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报")
    private Byte reportType;

    @Schema(description = "状态: 1-草稿 2-已提交")
    private Byte status;

    @Schema(description = "汇报人id")
    private Long userId;

    @Schema(description = "汇报日期-开始")
    private String reportDateBegin;

    @Schema(description = "汇报日期-结束")
    private String reportDateEnd;
}
