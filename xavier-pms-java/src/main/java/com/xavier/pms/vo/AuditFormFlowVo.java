package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批单流程出参")
public class AuditFormFlowVo implements Serializable {

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
     * 审批类型
     */
    @Schema(description = "审批类型")
    private String approvalType;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 审批方式 manual:人工审批 autoPass:自动通过
     */
    @Schema(description = "审批方式 manual:人工审批 autoPass:自动通过")
    private String approvalMode;

    /**
     * 多人时采用方式
     * 当审批类型为审批时：all:所有审批人同意 one:一名审批人同意即可 order:按顺序依次审批
     * 当审批类型为办理时：all:所有办理人提交 one:一名办理人提交即可 order:按顺序依次提交
     */
    @Schema(description = "多人时采用方式")
    private String approvalMoreType;

    /**
     * 审批状态
     */
    @Schema(description = "审批状态")
    private Byte auditStatus;

    /**
     * 审批单流程详细
     */
    @Schema(description = "审批单流程详细")
    private List<AuditFormFlowDetailVo> detailList;

    /**
     * 处理时间
     */
    @Schema(description = "处理时间")
    private LocalDateTime dealTime;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

}
