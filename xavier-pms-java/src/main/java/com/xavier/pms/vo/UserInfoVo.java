package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "登录用户信息")
public class UserInfoVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

    /**
     * token
     */
    @Schema(description = "token")
    private String token;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String employeeNumber;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickName;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 职位id
     */
    @Schema(description = "职位id")
    private Long postId;

    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private LocalDateTime expirationTime;

    /**
     * 是否管理员
     */
    @Schema(description = "是否管理员")
    private Boolean isAdmin;


}
