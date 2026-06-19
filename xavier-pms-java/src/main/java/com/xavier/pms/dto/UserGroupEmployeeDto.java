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
@Schema(description = "员工组员工列表入参")
public class UserGroupEmployeeDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @Schema(description = "用户id", required = true)
    @NotNull(message = "用户id不能为空")
    private Long id;

    /**
     * 昵称
     */
    @Schema(description = "昵称", required = true)
    @NotNull(message = "昵称不能为空")
    private String nickName;


}
