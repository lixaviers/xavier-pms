package com.xavier.pms.dto;

import com.xavier.pms.vo.ApprovalProcessJsonVo;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批入参")
public class ApprovalDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(description = "id", required = true)
    private Long id;

    /**
     * 审批名称
     */
    @Schema(description = "审批名称", required = true)
    @NotNull(message = "审批名称不能为空")
    @NotEmpty(message = "审批名称不能为空")
    @Size(max = 255, message = "审批名称不能超过255位")
    private String approvalName;

    /**
     * 审批分组id
     */
    @Schema(description = "审批分组id", required = true)
    @NotNull(message = "请选择审批分组")
    private Long approvalGroupId;

    /**
     * 图标
     */
    @Schema(description = "图标", required = true)
    @NotNull(message = "图标不能为空")
    @NotEmpty(message = "图标不能为空")
    @Size(max = 50, message = "图标不能超过50位")
    private String icon;

    /**
     * 审批状态
     */
    @Schema(description = "图标", required = true)
    @NotNull(message = "审批状态为空")
    private Byte appStatus;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;

    /**
     * 表单设计
     */
    @Schema(description = "表单设计")
    private String form;

    /**
     * 流程列表
     */
    @Schema(description = "流程列表")
    @NotNull(message = "流程列表不能为空")
    @Size(min = 2, message = "流程列表不能为空")
    private List<ApprovalProcessJsonVo> processList;


}
