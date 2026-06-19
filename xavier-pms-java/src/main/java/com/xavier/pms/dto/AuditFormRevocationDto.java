package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批单撤回入参")
public class AuditFormRevocationDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 备注
     */
    @Schema(description = "备注", required = true)
    @NotNull(message = "备注不能为空")
    @NotEmpty(message = "备注不能为空")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;


}
