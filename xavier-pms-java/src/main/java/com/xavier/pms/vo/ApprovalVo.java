package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批出参")
public class ApprovalVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 审批名称
     */
    @Schema(description = "审批名称")
    private String approvalName;

    /**
     * 审批分组id
     */
    @Schema(description = "审批分组id")
    private Long approvalGroupId;

    /**
     * 提交类型
     */
    @Schema(description = "提交类型")
    private String submitType;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 审批状态
     */
    @Schema(description = "审批状态")
    private Byte appStatus;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

}
