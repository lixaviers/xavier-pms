package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 汇报模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_report_template")
public class ReportTemplate {

    public static final LambdaQueryWrapper<ReportTemplate> gw() {
        return new LambdaQueryWrapper<>();
    }

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 模板名称 */
    @TableField(value = "template_name")
    private String templateName;

    /** 汇报类型: 1-日报 2-周报 3-月报 */
    @TableField(value = "report_type")
    private Byte reportType;

    /** 内容模板（JSON格式） */
    @TableField(value = "content_template")
    private String contentTemplate;

    /** 是否默认模板: 0-否 1-是 */
    @TableField(value = "is_default")
    private Boolean isDefault;

    /** 是否启用: 0-否 1-是 */
    @TableField(value = "is_enable")
    private Boolean isEnable;

    /** 备注 */
    @TableField(value = "remark")
    private String remark;

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
