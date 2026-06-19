package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批状态入参")
public class ApprovalStatusDto implements Serializable {

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
     * 审批状态
     */
    @Schema(description = "图标", required = true)
    @NotNull(message = "审批状态为空")
    private Byte appStatus;


}
