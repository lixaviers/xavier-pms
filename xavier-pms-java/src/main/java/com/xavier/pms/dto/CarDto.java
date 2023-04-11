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
@ApiModel("车辆入参")
public class CarDto implements Serializable {

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
     * 车辆名称
     */
    @ApiModelProperty(value = "车辆名称", required = true)
    @NotNull(message = "车辆名称不能为空")
    @NotEmpty(message = "车辆名称不能为空")
    @Size(max = 255, message = "车辆名称不能超过255位")
    private String carName;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    @Size(max = 50, message = "车牌号码不能超过50位")
    private String carNumber;

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
