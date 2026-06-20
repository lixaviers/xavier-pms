package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 离职记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_resignation")
public class Resignation {

    public static final LambdaQueryWrapper<Resignation> gw() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 离职类型: 1-主动离职 2-辞退 3-合同到期 4-其他
     */
    @TableField(value = "resignation_type")
    private Byte resignationType;

    /**
     * 离职日期
     */
    @TableField(value = "resignation_date")
    private LocalDate resignationDate;

    /**
     * 离职原因
     */
    @TableField(value = "resignation_reason")
    private String resignationReason;

    /**
     * 交接人id
     */
    @TableField(value = "handover_user_id")
    private Long handoverUserId;

    /**
     * 交接事项
     */
    @TableField(value = "handover_remark")
    private String handoverRemark;

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
     * 创建人id
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

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

}
