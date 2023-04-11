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
@ApiModel("员工卡片信息出参")
public class EmployeeCardVo implements Serializable {

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
     * 直属领导id
     */
    @ApiModelProperty("直属领导id")
    private Long directLeaderId;

    /**
     * 直属领导
     */
    @ApiModelProperty("直属领导")
    private String directLeader;

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
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
     * 职位名称
     */
    @ApiModelProperty("职位名称")
    private String postName;

    /**
     * 职称
     */
    @ApiModelProperty("职称")
    private String titleName;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
