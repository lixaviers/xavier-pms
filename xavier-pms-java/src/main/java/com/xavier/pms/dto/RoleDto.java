package com.xavier.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("角色入参")
public class RoleDto implements Serializable {

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
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotNull(message = "角色名称不能为空")
    @NotEmpty(message = "角色名称不能为空")
    @Size(max = 50, message = "角色名称不能超过50位")
    private String roleName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;

    /**
     * 菜单id列表
     */
    @ApiModelProperty(value = "菜单id列表")
    private List<Long> menuIdList;


}
