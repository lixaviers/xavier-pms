package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_user")
public class User {

    public static final LambdaQueryWrapper<User> gw() {
        return new LambdaQueryWrapper<>();
    }


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工号
     */
    @TableField(value = "employee_number")
    private String employeeNumber;

    /**
     * 姓名
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField(value = "user_pwd")
    private String userPwd;

    /**
     * 是否初始密码
     */
    @TableField(value = "is_init_pwd")
    private Boolean isInitPwd;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private Long departmentId;

    /**
     * 职位id
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     * 职称id
     */
    @TableField(value = "professional_title_id")
    private Long professionalTitleId;

    /**
     * 证件类型
     */
    @TableField(value = "document_type")
    private String documentType;

    /**
     * 证件号
     */
    @TableField(value = "document_number")
    private String documentNumber;

    /**
     * 出生日期
     */
    @TableField(value = "birth_date")
    private String birthDate;

    /**
     * 户籍类型
     */
    @TableField(value = "household_registration_type")
    private String householdRegistrationType;

    /**
     * 户籍地址
     */
    @TableField(value = "domicile_address")
    private String domicileAddress;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 民族
     */
    @TableField(value = "nationality")
    private String nationality;

    /**
     * 婚姻状况
     */
    @TableField(value = "marital_status")
    private String maritalStatus;

    /**
     * 政治面貌
     */
    @TableField(value = "political_status")
    private String politicalStatus;

    /**
     * 社保账号
     */
    @TableField(value = "social_security_account_number")
    private String socialSecurityAccountNumber;

    /**
     * 公积金账号
     */
    @TableField(value = "provident_fund_account")
    private String providentFundAccount;

    /**
     * 居住地址
     */
    @TableField(value = "residential_address")
    private String residentialAddress;

    /**
     * 最高学历
     */
    @TableField(value = "highest_degree")
    private String highestDegree;

    /**
     * 毕业院校
     */
    @TableField(value = "graduate_school")
    private String graduateSchool;

    /**
     * 毕业时间
     */
    @TableField(value = "graduation_time")
    private String graduationTime;

    /**
     * 所学专业
     */
    @TableField(value = "major")
    private String major;

    /**
     * 开户银行
     */
    @TableField(value = "deposit_bank")
    private String depositBank;

    /**
     * 银行卡号
     */
    @TableField(value = "bank_card_number")
    private String bankCardNumber;

    /**
     * 家庭信息
     */
    @TableField(value = "family_information")
    private String familyInformation;

    /**
     * 紧急联系人
     */
    @TableField(value = "emergency_contact")
    private String emergencyContact;

    /**
     * 是否管理员
     */
    @TableField(value = "is_admin")
    private Boolean isAdmin;

    /**
     * 状态
     */
    @TableField(value = "user_status")
    private Byte userStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean isDeleted;


    /**
     * -------------------------------------------------
     * 上面字段由工具自动生成，请在下面添加扩充字段
     * -------------------------------------------------
     */

    /**
     * 过期时间
     */
    @TableField(exist = false, value = "expiration_time")
    private LocalDateTime expirationTime;


}
