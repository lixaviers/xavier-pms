package com.xavier.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("个人卡片配置出参")
public class EmployeeCardConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否展示手机号")
    private Boolean showMobile;

    @ApiModelProperty("是否展示邮箱")
    private Boolean showEmail;

    @ApiModelProperty("是否展示部门")
    private Boolean showDept;

    @ApiModelProperty("是否展示职位")
    private Boolean showPosition;

}
