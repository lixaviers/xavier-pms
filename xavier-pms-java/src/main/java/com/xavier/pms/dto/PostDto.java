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
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "职位入参")
public class PostDto implements Serializable {

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
     * 职位名称
     */
    @Schema(description = "职位名称", required = true)
    @NotNull(message = "职位名称不能为空")
    @NotEmpty(message = "职位名称不能为空")
    @Size(max = 50, message = "职位名称不能超过50位")
    private String postName;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;

    /**
     * 角色id列表
     */
    @Schema(description = "角色id列表")
    private List<Long> roleIdList;


}
