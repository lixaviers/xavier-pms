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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批详情出参")
public class ApprovalDetailVo implements Serializable {

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
     * 审批名称
     */
    @ApiModelProperty("审批名称")
    private String approvalName;

    /**
     * 审批分组id
     */
    @ApiModelProperty("审批分组id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long approvalGroupId;

    /**
     * 提交类型
     */
    @ApiModelProperty("提交类型")
    private String submitType;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 审批状态
     */
    @ApiModelProperty("审批状态")
    private Byte appStatus;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 表单设计
     */
    @ApiModelProperty("表单设计")
    private String form;

    /**
     * 流程列表
     */
    @ApiModelProperty(value = "流程列表")
    private List<ApprovalProcessJsonVo> processList;

}
