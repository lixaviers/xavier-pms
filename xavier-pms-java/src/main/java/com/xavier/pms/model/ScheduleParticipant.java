package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 日程参与人
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_schedule_participant")
public class ScheduleParticipant {

    public static final LambdaQueryWrapper<ScheduleParticipant> gw() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日程id
     */
    @TableField(value = "schedule_id")
    private Long scheduleId;

    /**
     * 参与人id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 参与人姓名（冗余）
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 是否接受: 0-待确认 1-已接受 2-已拒绝
     */
    @TableField(value = "accept_status")
    private Byte acceptStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

}
