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
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("应用入参")
public class ApplicationDto implements Serializable {

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
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称", required = true)
    @NotNull(message = "应用名称不能为空")
    @NotEmpty(message = "应用名称不能为空")
    @Size(max = 255, message = "应用名称不能超过255位")
    private String appName;

    /**
     * 分组名称
     */
    @ApiModelProperty(value = "分组名称", required = true)
    @NotNull(message = "分组名称不能为空")
    @NotEmpty(message = "分组名称不能为空")
    @Size(max = 50, message = "分组名称不能超过50位")
    private String groupName;

    /**
     * 提交类型
     */
    @ApiModelProperty(value = "提交类型", required = true)
    @NotNull(message = "提交类型不能为空")
    @NotEmpty(message = "提交类型不能为空")
    @Size(max = 16, message = "提交类型不能超过16位")
    private String submitType;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", required = true)
    @NotNull(message = "图标不能为空")
    @NotEmpty(message = "图标不能为空")
    @Size(max = 50, message = "图标不能超过50位")
    private String icon;

    /**
     * 应用状态
     */
    @ApiModelProperty(value = "应用状态", required = true)
    @NotNull(message = "应用状态不能为空")
    private Byte appStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
