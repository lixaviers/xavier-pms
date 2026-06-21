package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工职位/职称变动详情出参")
public class PositionChangeVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "员工id")
    private Long userId;

    @Schema(description = "工号")
    private String employeeNumber;

    @Schema(description = "姓名")
    private String nickName;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "变动类型")
    private Byte changeType;

    @Schema(description = "变动类型描述")
    private String changeTypeDesc;

    @Schema(description = "变动前id")
    private Long beforeId;

    @Schema(description = "变动前名称")
    private String beforeName;

    @Schema(description = "变动后id")
    private Long afterId;

    @Schema(description = "变动后名称")
    private String afterName;

    @Schema(description = "变动日期")
    private LocalDate changeDate;

    @Schema(description = "变动原因")
    private String changeReason;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
