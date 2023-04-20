package com.xavier.pms.dto;

import com.xavier.pms.vo.ApplicationProcessJsonVo;
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
import java.util.List;


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
     * 应用分组id
     */
    @ApiModelProperty(value = "应用分组id", required = true)
    @NotNull(message = "请选择应用分组")
    private Long applicationGroupId;

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
    @ApiModelProperty(value = "图标", required = true)
    @NotNull(message = "应用状态为空")
    private Byte appStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;

    /**
     * 表单设计
     */
    @ApiModelProperty(value = "表单设计")
    private String form;

    /**
     * 流程列表
     */
    @ApiModelProperty(value = "流程列表")
    @NotNull(message = "流程列表不能为空")
    @Size(min = 2, message = "流程列表不能为空")
    private List<ApplicationProcessJsonVo> processList;


}
