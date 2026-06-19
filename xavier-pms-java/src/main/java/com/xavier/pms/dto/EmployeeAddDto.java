package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工录入入参")
public class EmployeeAddDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @Schema(description = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名不能超过50位")
    private String nickName;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @NotNull(message = "手机号不能为空")
    @NotEmpty(message = "手机号不能为空")
    @Size(max = 16, message = "手机号不能超过16位")
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Size(max = 255, message = "邮箱不能超过255位")
    private String email;

    /**
     * 别名
     */
    @Schema(description = "别名")
    @Size(max = 50, message = "别名不能超过50位")
    private String alias;

    /**
     * 首次参加工作日期
     */
    @Schema(description = "首次参加工作日期")
    private LocalDate firstEmploymentDate;

    /**
     * 直属领导id
     */
    @Schema(description = "直属领导id")
    @NotNull(message = "请选择直属领导")
    private Long directLeaderId;

    /**
     * 入职日期
     */
    @Schema(description = "入职日期")
    @NotNull(message = "请选择入职日期")
    private LocalDate entryDate;

    /**
     * 试用期(月)
     */
    @Schema(description = "试用期(月)")
    private Integer probationPeriod;

    /**
     * 预计转正日期
     */
    @Schema(description = "预计转正日期")
    private LocalDate estimatedConversionDate;

    /**
     * 试用期薪酬(月)
     */
    @Schema(description = "试用期薪酬(月)")
    private BigDecimal probationaryWage;

    /**
     * 转正薪酬(月)
     */
    @Schema(description = "转正薪酬(月)")
    private BigDecimal salary;

    /**
     * 部门id
     */
    @Schema(description = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long departmentId;

    /**
     * 职位id
     */
    @Schema(description = "职位id", required = true)
    @NotNull(message = "职位id不能为空")
    private Long postId;

    /**
     * 职称id
     */
    @Schema(description = "职称id")
    private Long professionalTitleId;

    /**
     * 证件类型
     */
    @Schema(description = "证件类型")
    @Size(max = 100, message = "证件类型不能超过100位")
    private String documentType;

    /**
     * 证件号
     */
    @Schema(description = "证件号")
    @Size(max = 100, message = "证件号不能超过100位")
    private String documentNumber;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private LocalDate birthDate;

    /**
     * 户籍类型
     */
    @Schema(description = "户籍类型")
    @Size(max = 100, message = "户籍类型不能超过100位")
    private String householdRegistrationType;

    /**
     * 户籍地址
     */
    @Schema(description = "户籍地址")
    @Size(max = 255, message = "户籍地址不能超过255位")
    private String domicileAddress;

    /**
     * 性别
     */
    @Schema(description = "性别")
    @Size(max = 100, message = "性别不能超过100位")
    private String gender;

    /**
     * 民族
     */
    @Schema(description = "民族")
    @Size(max = 100, message = "民族不能超过100位")
    private String nationality;

    /**
     * 婚姻状况
     */
    @Schema(description = "婚姻状况")
    @Size(max = 100, message = "婚姻状况不能超过100位")
    private String maritalStatus;

    /**
     * 政治面貌
     */
    @Schema(description = "政治面貌")
    @Size(max = 100, message = "政治面貌不能超过100位")
    private String politicalStatus;

    /**
     * 社保账号
     */
    @Schema(description = "社保账号")
    @Size(max = 100, message = "社保账号不能超过100位")
    private String socialSecurityAccountNumber;

    /**
     * 公积金账号
     */
    @Schema(description = "公积金账号")
    @Size(max = 100, message = "公积金账号不能超过100位")
    private String providentFundAccount;

    /**
     * 居住地址
     */
    @Schema(description = "居住地址")
    @Size(max = 255, message = "居住地址不能超过255位")
    private String residentialAddress;

    /**
     * 最高学历
     */
    @Schema(description = "最高学历")
    @Size(max = 100, message = "最高学历不能超过100位")
    private String highestDegree;

    /**
     * 毕业院校
     */
    @Schema(description = "毕业院校")
    @Size(max = 100, message = "毕业院校不能超过100位")
    private String graduateSchool;

    /**
     * 毕业时间
     */
    @Schema(description = "毕业时间")
    @Size(max = 100, message = "毕业时间不能超过100位")
    private String graduationTime;

    /**
     * 所学专业
     */
    @Schema(description = "所学专业")
    @Size(max = 100, message = "所学专业不能超过100位")
    private String major;

    /**
     * 开户银行
     */
    @Schema(description = "开户银行")
    @Size(max = 100, message = "开户银行不能超过100位")
    private String depositBank;

    /**
     * 银行卡号
     */
    @Schema(description = "银行卡号")
    @Size(max = 100, message = "银行卡号不能超过100位")
    private String bankCardNumber;

    /**
     * 工作经历列表
     */
    @Schema(description = "工作经历列表")
    @Valid
    private List<EmployeeWorkExperienceDto> workExperienceList;

    /**
     * 家庭信息列表
     */
    @Schema(description = "家庭信息列表")
    @NotNull(message = "家庭信息不能为空")
    @Size(min = 1, message = "家庭信息不能为空")
    @Valid
    private List<EmployeeInfoDto> familyInfoList;

    /**
     * 紧急联系人列表
     */
    @Schema(description = "紧急联系人列表")
    @NotNull(message = "紧急联系人不能为空")
    @Size(min = 1, message = "紧急联系人不能为空")
    @Valid
    private List<EmployeeInfoDto> emergencyContactList;


}
