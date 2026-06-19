package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "个人卡片配置入参")
public class EmployeeCardConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否展示手机号", required = true)
    @NotNull(message = "是否展示手机号不能为空")
    private Boolean showMobile;

    @Schema(description = "是否展示邮箱", required = true)
    @NotNull(message = "是否展示邮箱不能为空")
    private Boolean showEmail;

    @Schema(description = "是否展示部门", required = true)
    @NotNull(message = "是否展示部门不能为空")
    private Boolean showDept;

    @Schema(description = "是否展示职位", required = true)
    @NotNull(message = "是否展示职位不能为空")
    private Boolean showPosition;

}
