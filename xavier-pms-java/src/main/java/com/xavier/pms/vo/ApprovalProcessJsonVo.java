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
     * 审批类型
     */
    @ApiModelProperty("审批类型 start:提交 approval:审批 handler:办理 cc:抄送 end:结束")
    private String approvalType;

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
     * 审批方式
     */
    @ApiModelProperty("审批方式 manual:人工审批 autoPass:自动通过")
    private String approvalMode;

    /**
     * 审批人为空时类型
     * 办理人为空时类型
     */
    @ApiModelProperty("审批人为空时类型 autoPass:自动通过 employee:指定人员审批")
    private String emptyType;

    /**
     * 审批人为空时指定人员列表
     * 办理人为空时指定人员列表
     */
    @ApiModelProperty("审批人为空时指定人员列表")
    private List<EmployeeJsonVo> employeeList;

    /**
     * 多人时采用方式
     * 当审批类型为审批时：all:所有审批人同意 one:一名审批人同意即可 order:按顺序依次审批
     * 当审批类型为办理时：all:所有办理人提交 one:一名办理人提交即可 order:按顺序依次提交
     */
    @ApiModelProperty("多人时采用方式 all:所有人 one:一名即可 order:按顺序")
    private String approvalMoreType;

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
