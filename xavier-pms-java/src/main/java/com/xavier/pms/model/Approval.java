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
@TableName("tb_approval")
public class Approval {

    public static final LambdaQueryWrapper<Approval> gw() {
        return new LambdaQueryWrapper<>();
    }


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审批分组id
     */
    @TableField(value = "approval_group_id")
    private Long approvalGroupId;

    /**
     * 审批名称
     */
    @TableField(value = "approval_name")
    private String approvalName;

    /**
     * 提交类型
     */
    @TableField(value = "submit_type")
    private String submitType;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 表单json
     */
    @TableField(value = "form")
    private String form;

    /**
     * 流程json
     */
    @TableField(value = "process")
    private String process;

    /**
     * 审批状态
     */
    @TableField(value = "app_status")
    private Byte appStatus;

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
