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
 * 员工职位/职称变动记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_position_change")
public class PositionChange {

    public static final LambdaQueryWrapper<PositionChange> gw() {
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
     * 变动类型: 1-职位变动 2-职称变动
     */
    @TableField(value = "change_type")
    private Byte changeType;

    /**
     * 变动前id
     */
    @TableField(value = "before_id")
    private Long beforeId;

    /**
     * 变动前名称
     */
    @TableField(value = "before_name")
    private String beforeName;

    /**
     * 变动后id
     */
    @TableField(value = "after_id")
    private Long afterId;

    /**
     * 变动后名称
     */
    @TableField(value = "after_name")
    private String afterName;

    /**
     * 变动日期
     */
    @TableField(value = "change_date")
    private LocalDate changeDate;

    /**
     * 变动原因
     */
    @TableField(value = "change_reason")
    private String changeReason;

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
