package com.xavier.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工组入参")
public class UserGroupDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    /**
     * 组名称
     */
    @ApiModelProperty(value = "组名称", required = true)
    @NotNull(message = "组名称不能为空")
    @NotEmpty(message = "组名称不能为空")
    @Size(max = 50, message = "组名称不能超过50位")
    private String groupName;

    /**
     * 员工列表
     */
    @ApiModelProperty(value = "员工列表")
    @NotNull(message = "员工列表不能为空")
    @Size(max = 200, message = "员工列表不能超过200位")
    @Valid
    private List<UserGroupEmployeeDto> employeeList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;


}
