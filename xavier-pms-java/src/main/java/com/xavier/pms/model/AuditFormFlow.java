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
@TableName("tb_audit_form_flow")
public class AuditFormFlow {

    public static final LambdaQueryWrapper<AuditFormFlow> gw() {
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
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 审批类型
     */
    @TableField(value = "approval_type")
    private String approvalType;

    /**
     * 审批方式 manual:人工审批 autoPass:自动通过
     */
    @TableField(value = "approval_mode")
    private String approvalMode;

    /**
     * 多人时采用方式
     * 当审批类型为审批时：all:所有审批人同意 one:一名审批人同意即可 order:按顺序依次审批
     * 当审批类型为办理时：all:所有办理人提交 one:一名办理人提交即可 order:按顺序依次提交
     */
    @TableField(value = "approval_more_type")
    private String approvalMoreType;

    /**
     * 流程节点
     */
    @TableField(value = "process_node")
    private Integer processNode;

    /**
     * 审批状态
     */
    @TableField(value = "audit_status")
    private Byte auditStatus;

    /**
     * 处理时间
     */
    @TableField(value = "deal_time")
    private LocalDateTime dealTime;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

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


    /**
     * -------------------------------------------------
     * 上面字段由工具自动生成，请在下面添加扩充字段
     * -------------------------------------------------
     */


}
