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
@TableName("tb_audit_form_flow_detail")
public class AuditFormFlowDetail {

    public static final LambdaQueryWrapper<AuditFormFlowDetail> gw() {
        return new LambdaQueryWrapper<>();
    }


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审批单id
     */
    @TableField(value = "audit_form_id")
    private Long auditFormId;

    /**
     * 审批单流程id
     */
    @TableField(value = "audit_form_flow_id")
    private Long auditFormFlowId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 姓名
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 审批类型
     */
    @TableField(value = "approval_type")
    private String approvalType;

    /**
     * 排序
     */
    @TableField(value = "sort_number")
    private Integer sortNumber;

    /**
     * 审批状态
     */
    @TableField(value = "audit_status")
    private Byte auditStatus;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 处理时间
     */
    @TableField(value = "deal_time")
    private LocalDateTime dealTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

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

    public AuditFormFlowDetail(Long id, Byte auditStatus, LocalDateTime dealTime) {
        this.id = id;
        this.auditStatus = auditStatus;
        this.dealTime = dealTime;
    }


}
