package com.xavier.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批流程json出参")
public class ApprovalProcessJsonVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ApiModelProperty("类型 start:提交 approval:审批 handler:办理 cc:抄送 end:结束")
    private String type;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 提交类型
     */
    @ApiModelProperty("提交类型 all:全员 employee:指定人员")
    private String submitType;

    /**
     * 审批类型
     */
    @ApiModelProperty("提交类型 manual:人工审批 autoPass:自动通过")
    private String approvalType;

    /**
     * 为空时类型
     */
    @ApiModelProperty("为空时类型 autoPass:自动通过 employee:指定人员审批")
    private String emptyType;

    /**
     * 人员列表
     */
    @ApiModelProperty("人员列表")
    private List<EmployeeJsonVo> employeeList;

    /**
     * 列表
     */
    @ApiModelProperty("列表")
    private List<ApprovalProcessItemJsonVo> itemList;

    /**
     * 抄送列表
     */
    @ApiModelProperty("抄送列表")
    private List<ApprovalProcessItemJsonVo> ccList;


}
