package com.xavier.pms.dto;

import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工查询入参")
public class EmployeeQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "工号")
    private String employeeNumber;

    @Schema(description = "部门id")
    private String departmentId;

    @Schema(description = "姓名")
    private String nickName;

    @Schema(description = "拼音首字母")
    private String py;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "状态", hidden = true)
    private Byte userStatus = UserStatusEnum.NORMAL.getValue();


}
