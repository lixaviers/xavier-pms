package com.xavier.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("个人卡片配置入参")
public class EmployeeCardConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否展示手机号", required = true)
    @NotNull(message = "是否展示手机号不能为空")
    private Boolean showMobile;

    @ApiModelProperty(value = "是否展示邮箱", required = true)
    @NotNull(message = "是否展示邮箱不能为空")
    private Boolean showEmail;

    @ApiModelProperty(value = "是否展示部门", required = true)
    @NotNull(message = "是否展示部门不能为空")
    private Boolean showDept;

    @ApiModelProperty(value = "是否展示职位", required = true)
    @NotNull(message = "是否展示职位不能为空")
    private Boolean showPosition;

}
