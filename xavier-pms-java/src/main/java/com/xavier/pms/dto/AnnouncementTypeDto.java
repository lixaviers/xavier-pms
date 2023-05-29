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
@ApiModel("公告类型入参")
public class AnnouncementTypeDto implements Serializable {

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
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", required = true)
    @NotNull(message = "类型名称不能为空")
    @NotEmpty(message = "类型名称不能为空")
    @Size(max = 255, message = "类型名称不能超过255位")
    private String typeName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sortNumber;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
