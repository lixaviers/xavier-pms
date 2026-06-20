package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "离职记录详情出参")
public class ResignationVo implements Serializable {

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

    @Schema(description = "职位名称")
    private String postName;

    @Schema(description = "离职类型")
    private Byte resignationType;

    @Schema(description = "离职类型描述")
    private String resignationTypeDesc;

    @Schema(description = "离职日期")
    private LocalDate resignationDate;

    @Schema(description = "离职原因")
    private String resignationReason;

    @Schema(description = "交接人id")
    private Long handoverUserId;

    @Schema(description = "交接人姓名")
    private String handoverUserName;

    @Schema(description = "交接事项")
    private String handoverRemark;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
