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
 * 工作汇报
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_work_report")
public class WorkReport {

    public static final LambdaQueryWrapper<WorkReport> gw() {
        return new LambdaQueryWrapper<>();
    }

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 汇报标题 */
    @TableField(value = "title")
    private String title;

    /** 汇报类型: 1-日报 2-周报 3-月报 */
    @TableField(value = "report_type")
    private Byte reportType;

    /** 使用的模板id */
    @TableField(value = "template_id")
    private Long templateId;

    /** 汇报人id */
    @TableField(value = "user_id")
    private Long userId;

    /** 汇报日期 */
    @TableField(value = "report_date")
    private LocalDate reportDate;

    /** 第几周（周报用） */
    @TableField(value = "week_number")
    private Integer weekNumber;

    /** 第几月（月报用） */
    @TableField(value = "month_number")
    private Integer monthNumber;

    /** 汇报内容（JSON格式） */
    @TableField(value = "content")
    private String content;

    /** 工作总结 */
    @TableField(value = "work_summary")
    private String workSummary;

    /** 工作计划 */
    @TableField(value = "work_plan")
    private String workPlan;

    /** 问题与建议 */
    @TableField(value = "issues")
    private String issues;

    /** 状态: 1-草稿 2-已提交 */
    @TableField(value = "status")
    private Byte status;

    /** 提交时间 */
    @TableField(value = "submit_time")
    private LocalDateTime submitTime;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 创建人id */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    /** 修改时间 */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /** 是否删除 */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean isDeleted;
}
