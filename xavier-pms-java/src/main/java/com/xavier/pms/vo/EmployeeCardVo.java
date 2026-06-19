package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工卡片信息出参")
public class EmployeeCardVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String employeeNumber;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String nickName;

    /**
     * 直属领导id
     */
    @Schema(description = "直属领导id")
    private Long directLeaderId;

    /**
     * 直属领导
     */
    @Schema(description = "直属领导")
    private String directLeader;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
    private String postName;

    /**
     * 职称
     */
    @Schema(description = "职称")
    private String titleName;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String gender;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
