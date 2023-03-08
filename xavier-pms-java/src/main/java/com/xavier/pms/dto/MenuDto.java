package com.xavier.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("菜单入参")
public class MenuDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", required = true)
    @NotNull(message = "父id不能为空")
    private Long parentId;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码", required = true)
    @Size(max = 100, message = "菜单编码不能超过100位")
    private String menuCode;

    /**
     * 菜单类型 M.目录 C.菜单 I.接口
     */
    @ApiModelProperty(value = "菜单类型 M.目录 C.菜单 I.接口", required = true)
    @NotNull(message = "菜单类型不能为空")
    @NotEmpty(message = "菜单类型不能为空")
    @Size(max = 8, message = "菜单类型不能超过8位")
    private String menuType;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    @NotNull(message = "菜单名称不能为空")
    @NotEmpty(message = "菜单名称不能为空")
    @Size(max = 100, message = "菜单名称不能超过100位")
    private String menuName;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    @Size(max = 100, message = "组件路径不能超过100位")
    private String component;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @Size(max = 255, message = "地址不能超过255位")
    private String url;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 50, message = "图标不能超过50位")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sortNumber;

    /**
     * 是否外链
     */
    @ApiModelProperty(value = "是否外链", required = true)
    @NotNull(message = "是否外链不能为空")
    private Boolean isFrame;

    /**
     * 路由参数
     */
    @ApiModelProperty(value = "路由参数")
    @Size(max = 255, message = "路由参数不能超过255位")
    private String query;

    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否缓存", required = true)
    @NotNull(message = "是否缓存不能为空")
    private Boolean isCache;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnable;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", required = true)
    @NotNull(message = "是否显示不能为空")
    private Boolean isShow;


}
