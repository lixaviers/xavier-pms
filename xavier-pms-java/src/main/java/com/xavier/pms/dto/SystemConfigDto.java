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
@Schema(description = "系统配置入参")
public class SystemConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "配置键", required = true)
    @NotNull(message = "配置键不能为空")
    @NotEmpty(message = "配置键不能为空")
    @Size(max = 100, message = "配置键不能超过100位")
    private String configKey;

    @Schema(description = "配置值")
    @Size(max = 500, message = "配置值不能超过500位")
    private String configValue;

    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;

}
