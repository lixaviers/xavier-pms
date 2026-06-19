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
@Schema(description = "审批单出参")
public class AuditFormVo implements Serializable {

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
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String nickName;

    /**
     * 审批id
     */
    @Schema(description = "审批id")
    private Long approvalId;

    /**
     * 审批名称
     */
    @Schema(description = "审批名称")
    private String approvalName;

    /**
     * 表单json
     */
    @Schema(description = "表单json")
    private String form;

    /**
     * 表单数据
     */
    @Schema(description = "表单数据")
    private String formData;

    /**
     * 审批流程
     */
    @Schema(description = "审批流程")
    private String approvalProcess;

    /**
     * 审批状态
     */
    @Schema(description = "审批状态")
    private Byte auditStatus;

    /**
     * 审批状态描述
     */
    @Schema(description = "审批状态描述")
    private String auditStatusDesc;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 展示操作按钮
     */
    @Schema(description = "展示操作按钮")
    private Button button;

    @Data
    @ToString(callSuper = true)
    @Schema(description = "审批单按钮出参")
    public static class Button {

        @Schema(description = "是否展示审批按钮")
        private Boolean audit;

        @Schema(description = "是否展示办理按钮")
        private Boolean handler;

        @Schema(description = "是否展示撤回按钮")
        private Boolean revocation;

        public Button() {
            this.audit = false;
            this.handler = false;
            this.revocation = false;
        }
    }

}
