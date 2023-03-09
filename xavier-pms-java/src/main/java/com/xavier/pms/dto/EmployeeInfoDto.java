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


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工信息入参")
public class EmployeeInfoDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名不能超过50位")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "关系", required = true)
    @NotNull(message = "关系不能为空")
    @NotEmpty(message = "关系不能为空")
    private String relation;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "联系方式")
    @NotNull(message = "联系方式不能为空")
    @NotEmpty(message = "联系方式不能为空")
    @Size(max = 16, message = "联系方式不能超过16位")
    private String mobile;


}
