package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("用户查询入参")
public class UserQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("工号")
    private String employeeNumber;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("密码")
    private String userPwd;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("部门id")
    private Long departmentId;

    @ApiModelProperty("职位id")
    private Long postId;

    @ApiModelProperty("职称id")
    private Long professionalTitleId;

    @ApiModelProperty("证件类型")
    private String documentType;

    @ApiModelProperty("证件号")
    private String documentNumber;

    @ApiModelProperty("出生日期起")
    private String birthDateFrom;

    @ApiModelProperty("出生日期止")
    private String birthDateTo;

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

    @ApiModelProperty("毕业时间起")
    private String graduationTimeFrom;

    @ApiModelProperty("毕业时间止")
    private String graduationTimeTo;

    @ApiModelProperty("所学专业")
    private String major;

    @ApiModelProperty("开户银行")
    private String depositBank;

    @ApiModelProperty("银行卡号")
    private String bankCardNumber;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    @ApiModelProperty("创建时间起")
    private LocalDateTime createTimeFrom;

    @ApiModelProperty("创建时间止")
    private LocalDateTime createTimeTo;


}
