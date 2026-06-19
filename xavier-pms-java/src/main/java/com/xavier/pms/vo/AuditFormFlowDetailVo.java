package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "审批单流程详细出参")
public class AuditFormFlowDetailVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id")
    private Long userId;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String nickName;

    /**
     * 审批类型
     */
    @Schema(description = "审批类型")
    private String approvalType;

    /**
     * 审批状态
     */
    @Schema(description = "审批状态")
    private Byte auditStatus;

}
