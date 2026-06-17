package com.xavier.pms.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xavier.pms.utils.LongJsonDeserializer;
import com.xavier.pms.utils.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("个人详细信息出参")
public class UserProfileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty("工号")
    private String employeeNumber;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("别名")
    private String alias;

    @ApiModelProperty("首次参加工作日期")
    private LocalDate firstEmploymentDate;

    @ApiModelProperty("直属领导id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long directLeaderId;

    @ApiModelProperty("直属领导姓名")
    private String directLeaderName;

    @ApiModelProperty("入职日期")
    private LocalDate entryDate;

    @ApiModelProperty("试用期(月)")
    private Integer probationPeriod;

    @ApiModelProperty("预计转正日期")
    private LocalDate estimatedConversionDate;

    @ApiModelProperty("部门id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long departmentId;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("职位id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long postId;

    @ApiModelProperty("职位名称")
    private String postName;

    @ApiModelProperty("职称id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long professionalTitleId;

    @ApiModelProperty("职称名称")
    private String professionalTitleName;

    @ApiModelProperty("证件类型")
    private String documentType;

    @ApiModelProperty("证件号")
    private String documentNumber;

    @ApiModelProperty("出生日期")
    private LocalDate birthDate;

    @ApiModelProperty("户籍类型")
    private String householdRegistrationType;

    @ApiModelProperty("户籍地址")
    private String domicileAddress;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("民族")
    private String nationality;

    @ApiModelProperty("婚姻状况")
    private String maritalStatus;

    @ApiModelProperty("政治面貌")
    private String politicalStatus;

    @ApiModelProperty("社保账号")
    private String socialSecurityAccountNumber;

    @ApiModelProperty("公积金账号")
    private String providentFundAccount;

    @ApiModelProperty("居住地址")
    private String residentialAddress;

    @ApiModelProperty("最高学历")
    private String highestDegree;

    @ApiModelProperty("毕业院校")
    private String graduateSchool;

    @ApiModelProperty("毕业时间")
    private String graduationTime;

    @ApiModelProperty("所学专业")
    private String major;

    @ApiModelProperty("开户银行")
    private String depositBank;

    @ApiModelProperty("银行卡号")
    private String bankCardNumber;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    @ApiModelProperty("状态")
    private Byte userStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
