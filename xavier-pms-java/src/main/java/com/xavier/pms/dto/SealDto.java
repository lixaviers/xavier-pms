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
@ApiModel("印章入参")
public class SealDto implements Serializable {

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
     * 印章名称
     */
    @ApiModelProperty(value = "印章名称", required = true)
    @NotNull(message = "印章名称不能为空")
    @NotEmpty(message = "印章名称不能为空")
    @Size(max = 255, message = "印章名称不能超过255位")
    private String sealName;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
