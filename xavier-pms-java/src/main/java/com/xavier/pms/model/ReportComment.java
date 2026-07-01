package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汇报评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_report_comment")
public class ReportComment {

    public static final LambdaQueryWrapper<ReportComment> gw() {
        return new LambdaQueryWrapper<>();
    }

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 汇报id */
    @TableField(value = "report_id")
    private Long reportId;

    /** 评论人id */
    @TableField(value = "user_id")
    private Long userId;

    /** 评论内容 */
    @TableField(value = "content")
    private String content;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 是否删除 */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean isDeleted;
}
