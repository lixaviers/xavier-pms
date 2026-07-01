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
 * 汇报模板新增/编辑入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "汇报模板新增/编辑入参")
public class ReportTemplateAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id（编辑时传入）")
    private Long id;

    @Schema(description = "模板名称", required = true)
    @NotBlank(message = "模板名称不能为空")
    @Size(max = 100, message = "模板名称不能超过100位")
    private String templateName;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报", required = true)
    @NotNull(message = "汇报类型不能为空")
    private Byte reportType;

    @Schema(description = "内容模板（JSON格式）")
    private String contentTemplate;

    @Schema(description = "是否默认模板")
    private Boolean isDefault;

    @Schema(description = "是否启用")
    private Boolean isEnable;

    @Schema(description = "备注")
    @Size(max = 500, message = "备注不能超过500位")
    private String remark;
}
