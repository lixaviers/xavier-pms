package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "离职操作入参")
public class ResignationAddDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    @Schema(description = "员工id", required = true)
    @NotNull(message = "员工id不能为空")
    private Long userId;

    /**
     * 离职类型: 1-主动离职 2-辞退 3-合同到期 4-其他
     */
    @Schema(description = "离职类型: 1-主动离职 2-辞退 3-合同到期 4-其他", required = true)
    @NotNull(message = "离职类型不能为空")
    private Byte resignationType;

    /**
     * 离职日期
     */
    @Schema(description = "离职日期", required = true)
    @NotNull(message = "离职日期不能为空")
    private LocalDate resignationDate;

    /**
     * 离职原因
     */
    @Schema(description = "离职原因")
    @Size(max = 500, message = "离职原因不能超过500位")
    private String resignationReason;

    /**
     * 交接人id
     */
    @Schema(description = "交接人id")
    private Long handoverUserId;

    /**
     * 交接事项
     */
    @Schema(description = "交接事项")
    @Size(max = 500, message = "交接事项不能超过500位")
    private String handoverRemark;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注不能超过500位")
    private String remarks;

}
