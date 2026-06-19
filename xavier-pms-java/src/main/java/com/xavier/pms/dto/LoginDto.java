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
@Schema(description = "用户登录入参")
public class LoginDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 用户名
     */
    @Schema(description = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 50, message = "用户名不能超过50位")
    private String userName;

    /**
     * 密码
     */
    @Schema(description = "密码", required = true)
    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String userPwd;

    /**
     * 验证码
     */
    @Schema(description = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    @NotEmpty(message = "验证码不能为空")
    private String verCode;

    /**
     * 验证码uuid
     */
    @Schema(description = "验证码uuid", required = true)
    @NotNull(message = "验证码uuid不能为空")
    @NotEmpty(message = "验证码uuid不能为空")
    private String uuid;


}
