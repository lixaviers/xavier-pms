package com.xavier.pms.dto;

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
@Schema(description = "离职记录查询入参")
public class ResignationQueryDto extends QueryDto {

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

    @Schema(description = "离职类型: 1-主动离职 2-辞退 3-合同到期 4-其他")
    private Byte resignationType;

}
