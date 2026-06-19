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
@Schema(description = "部门入参")
public class DepartmentDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id", required = true)
    private Long id;

    /**
     * 父id
     */
    @Schema(description = "父id", required = true)
    @NotNull(message = "父id不能为空")
    private Long parentId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称", required = true)
    @NotNull(message = "部门名称不能为空")
    @NotEmpty(message = "部门名称不能为空")
    @Size(max = 100, message = "部门名称不能超过100位")
    private String deptName;

    /**
     * 负责人id
     */
    @Schema(description = "负责人id", required = true)
    private Long userId;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;


}
