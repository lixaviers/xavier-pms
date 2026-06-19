package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "个人详细信息出参")
public class UserProfileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "工号")
    private String employeeNumber;

    @Schema(description = "姓名")
    private String nickName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "别名")
    private String alias;

    @Schema(description = "首次参加工作日期")
    private LocalDate firstEmploymentDate;

    @Schema(description = "直属领导id")
    private Long directLeaderId;

    @Schema(description = "直属领导姓名")
    private String directLeaderName;

    @Schema(description = "入职日期")
    private LocalDate entryDate;

    @Schema(description = "试用期(月)")
    private Integer probationPeriod;

    @Schema(description = "预计转正日期")
    private LocalDate estimatedConversionDate;

    @Schema(description = "部门id")
    private Long departmentId;

    @Schema(description = "部门名称")
    private String departmentName;

    @Schema(description = "职位id")
    private Long postId;

    @Schema(description = "职位名称")
    private String postName;

    @Schema(description = "职称id")
    private Long professionalTitleId;

    @Schema(description = "职称名称")
    private String professionalTitleName;

    @Schema(description = "证件类型")
    private String documentType;

    @Schema(description = "证件号")
    private String documentNumber;

    @Schema(description = "出生日期")
    private LocalDate birthDate;

    @Schema(description = "户籍类型")
    private String householdRegistrationType;

    @Schema(description = "户籍地址")
    private String domicileAddress;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "民族")
    private String nationality;

    @Schema(description = "婚姻状况")
    private String maritalStatus;

    @Schema(description = "政治面貌")
    private String politicalStatus;

    @Schema(description = "社保账号")
    private String socialSecurityAccountNumber;

    @Schema(description = "公积金账号")
    private String providentFundAccount;

    @Schema(description = "居住地址")
    private String residentialAddress;

    @Schema(description = "最高学历")
    private String highestDegree;

    @Schema(description = "毕业院校")
    private String graduateSchool;

    @Schema(description = "毕业时间")
    private String graduationTime;

    @Schema(description = "所学专业")
    private String major;

    @Schema(description = "开户银行")
    private String depositBank;

    @Schema(description = "银行卡号")
    private String bankCardNumber;

    @Schema(description = "是否管理员")
    private Boolean isAdmin;

    @Schema(description = "状态")
    private Byte userStatus;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
