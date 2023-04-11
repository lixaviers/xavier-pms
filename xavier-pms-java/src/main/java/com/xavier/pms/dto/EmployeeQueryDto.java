package com.xavier.pms.dto;

import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工查询入参")
public class EmployeeQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工号")
    private String employeeNumber;

    @ApiModelProperty("部门id")
    private String departmentId;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("拼音首字母")
    private String py;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "状态", hidden = true)
    private Byte userStatus = UserStatusEnum.NORMAL.getValue();


}
