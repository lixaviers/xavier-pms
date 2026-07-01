package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 汇报模板出参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "汇报模板出参")
public class ReportTemplateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "模板名称")
    private String templateName;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报")
    private Byte reportType;

    @Schema(description = "汇报类型描述")
    private String reportTypeDesc;

    @Schema(description = "内容模板（JSON格式）")
    private String contentTemplate;

    @Schema(description = "是否默认模板")
    private Boolean isDefault;

    @Schema(description = "是否启用")
    private Boolean isEnable;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人姓名")
    private String createUserName;
}
