package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "车辆入参")
public class CarDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id", required = true)
    private Long id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "用户不能为空")
    private Long userId;

    /**
     * 车辆名称
     */
    @Schema(description = "车辆名称", required = true)
    @NotEmpty(message = "车辆名称不能为空")
    @Size(max = 255, message = "车辆名称不能超过255位")
    private String carName;

    /**
     * 车牌号码
     */
    @Schema(description = "车牌号码")
    @Size(max = 50, message = "车牌号码不能超过50位")
    private String carNumber;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnable;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
