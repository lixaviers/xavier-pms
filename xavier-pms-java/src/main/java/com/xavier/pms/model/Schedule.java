package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 日程
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_schedule")
public class Schedule {

    public static final LambdaQueryWrapper<Schedule> gw() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日程标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 日程类型: 1-个人日程 2-会议
     */
    @TableField(value = "schedule_type")
    private Byte scheduleType;

    /**
     * 创建人id（日程拥有者）
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 是否全天: 0-否 1-是
     */
    @TableField(value = "is_all_day")
    private Boolean isAllDay;

    /**
     * 地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 会议室id
     */
    @TableField(value = "meeting_room_id")
    private Long meetingRoomId;

    /**
     * 提醒方式: 0-不提醒 1-提前5分钟 2-提前15分钟 3-提前30分钟 4-提前1小时 5-提前1天
     */
    @TableField(value = "remind_type")
    private Byte remindType;

    /**
     * 颜色标签
     */
    @TableField(value = "color")
    private String color;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 是否公开: 0-仅自己可见 1-团队可见
     */
    @TableField(value = "is_public")
    private Boolean isPublic;

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
