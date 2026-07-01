package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 汇报模板查询入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "汇报模板查询入参")
public class ReportTemplateQueryDto extends QueryDto {

    private static final long serialVersionUID = 1L;

    @Schema(description = "模板名称")
    private String templateName;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报")
    private Byte reportType;

    @Schema(description = "是否启用")
    private Boolean isEnable;
}
