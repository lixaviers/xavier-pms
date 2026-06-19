package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工组入参")
public class UserGroupDto implements Serializable {

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
     * 用户id
     */
    @Schema(description = "用户id", hidden = true)
    private Long userId;

    /**
     * 组名称
     */
    @Schema(description = "组名称", required = true)
    @NotNull(message = "组名称不能为空")
    @NotEmpty(message = "组名称不能为空")
    @Size(max = 50, message = "组名称不能超过50位")
    private String groupName;

    /**
     * 员工列表
     */
    @Schema(description = "员工列表")
    @NotNull(message = "员工列表不能为空")
    @Size(max = 200, message = "员工列表不能超过200位")
    @Valid
    private List<UserGroupEmployeeDto> employeeList;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;


}
