package com.xavier.pms.model;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_menu")
public class Menu {

    public static final LambdaQueryWrapper<Menu> gw() {
        return new LambdaQueryWrapper<>();
    }


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 菜单编码
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单类型 M.目录 C.菜单 I.接口
     */
    @TableField(value = "menu_type")
    private String menuType;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "sort_number")
    private Integer sortNumber;

    /**
     * 是否外链
     */
    @TableField(value = "is_frame")
    private Boolean isFrame;

    /**
     * 路由参数
     */
    @TableField(value = "query")
    private String query;

    /**
     * 是否缓存
     */
    @TableField(value = "is_cache")
    private Boolean isCache;

    /**
     * 是否显示
     */
    @TableField(value = "is_show")
    private Boolean isShow;

    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Boolean isEnable;

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


    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 增加子菜单
     */
    public void addChildren(Menu menu) {
        if (CollUtil.isEmpty(children)) {
            children = new ArrayList<>();
        }
        children.add(menu);
    }


}
