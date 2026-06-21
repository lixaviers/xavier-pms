package com.xavier.pms.dto;

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
@Schema(description = "员工职位/职称变动入参")
public class PositionChangeAddDto implements Serializable {

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
     * 变动类型: 1-职位变动 2-职称变动
     */
    @Schema(description = "变动类型: 1-职位变动 2-职称变动", required = true)
    @NotNull(message = "变动类型不能为空")
    private Byte changeType;

    /**
     * 变动前id
     */
    @Schema(description = "变动前id")
    private Long beforeId;

    /**
     * 变动前名称
     */
    @Schema(description = "变动前名称")
    private String beforeName;

    /**
     * 变动后id
     */
    @Schema(description = "变动后id", required = true)
    @NotNull(message = "变动后id不能为空")
    private Long afterId;

    /**
     * 变动后名称
     */
    @Schema(description = "变动后名称")
    private String afterName;

    /**
     * 变动日期
     */
    @Schema(description = "变动日期", required = true)
    @NotNull(message = "变动日期不能为空")
    private LocalDate changeDate;

    /**
     * 变动原因
     */
    @Schema(description = "变动原因")
    @Size(max = 500, message = "变动原因不能超过500位")
    private String changeReason;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注不能超过500位")
    private String remarks;

}
