package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "个人卡片配置出参")
public class EmployeeCardConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否展示手机号")
    private Boolean showMobile;

    @Schema(description = "是否展示邮箱")
    private Boolean showEmail;

    @Schema(description = "是否展示部门")
    private Boolean showDept;

    @Schema(description = "是否展示职位")
    private Boolean showPosition;

}
