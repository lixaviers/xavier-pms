package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工职位/职称变动查询入参")
public class PositionChangeQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    private String nickName;

    @Schema(description = "工号")
    private String employeeNumber;

    @Schema(description = "部门id")
    private String departmentId;

    @Schema(description = "变动类型: 1-职位变动 2-职称变动")
    private Byte changeType;

}
