package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * 工作汇报新增/编辑入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "工作汇报新增/编辑入参")
public class WorkReportAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id（编辑时传入）")
    private Long id;

    @Schema(description = "汇报标题", required = true)
    @NotBlank(message = "汇报标题不能为空")
    @Size(max = 200, message = "汇报标题不能超过200位")
    private String title;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报", required = true)
    @NotNull(message = "汇报类型不能为空")
    private Byte reportType;

    @Schema(description = "使用的模板id")
    private Long templateId;

    @Schema(description = "汇报日期", required = true)
    @NotBlank(message = "汇报日期不能为空")
    private String reportDate;

    @Schema(description = "汇报内容（JSON格式）")
    private String content;

    @Schema(description = "工作总结")
    private String workSummary;

    @Schema(description = "工作计划")
    private String workPlan;

    @Schema(description = "问题与建议")
    private String issues;

    @Schema(description = "状态: 1-草稿 2-已提交")
    private Byte status;
}
