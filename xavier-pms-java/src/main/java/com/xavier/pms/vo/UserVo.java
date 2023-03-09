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
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("用户出参")
public class UserVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty("id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 工号
     */
    @ApiModelProperty("工号")
    private String employeeNumber;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String userPwd;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long departmentId;

    /**
     * 职位id
     */
    @ApiModelProperty("职位id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long postId;

    /**
     * 职称id
     */
    @ApiModelProperty("职称id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long professionalTitleId;

    /**
     * 证件类型
     */
    @ApiModelProperty("证件类型")
    private String documentType;

    /**
     * 证件号
     */
    @ApiModelProperty("证件号")
    private String documentNumber;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private String birthDate;

    /**
     * 户籍类型
     */
    @ApiModelProperty("户籍类型")
    private String householdRegistrationType;

    /**
     * 户籍地址
     */
    @ApiModelProperty("户籍地址")
    private String domicileAddress;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 民族
     */
    @ApiModelProperty("民族")
    private String nationality;

    /**
     * 婚姻状况
     */
    @ApiModelProperty("婚姻状况")
    private String maritalStatus;

    /**
     * 政治面貌
     */
    @ApiModelProperty("政治面貌")
    private String politicalStatus;

    /**
     * 社保账号
     */
    @ApiModelProperty("社保账号")
    private String socialSecurityAccountNumber;

    /**
     * 公积金账号
     */
    @ApiModelProperty("公积金账号")
    private String providentFundAccount;

    /**
     * 居住地址
     */
    @ApiModelProperty("居住地址")
    private String residentialAddress;

    /**
     * 最高学历
     */
    @ApiModelProperty("最高学历")
    private String highestDegree;

    /**
     * 毕业院校
     */
    @ApiModelProperty("毕业院校")
    private String graduateSchool;

    /**
     * 毕业时间
     */
    @ApiModelProperty("毕业时间")
    private String graduationTime;

    /**
     * 所学专业
     */
    @ApiModelProperty("所学专业")
    private String major;

    /**
     * 开户银行
     */
    @ApiModelProperty("开户银行")
    private String depositBank;

    /**
     * 银行卡号
     */
    @ApiModelProperty("银行卡号")
    private String bankCardNumber;

    /**
     * 家庭信息
     */
    @ApiModelProperty("家庭信息")
    private String familyInformation;

    /**
     * 紧急联系人
     */
    @ApiModelProperty("紧急联系人")
    private String emergencyContact;

    /**
     * 是否管理员
     */
    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
