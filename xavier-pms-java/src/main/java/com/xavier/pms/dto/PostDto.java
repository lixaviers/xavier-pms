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
@ApiModel("职位入参")
public class PostDto implements Serializable {

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
     * 职位名称
     */
    @ApiModelProperty(value = "职位名称", required = true)
    @NotNull(message = "职位名称不能为空")
    @NotEmpty(message = "职位名称不能为空")
    @Size(max = 50, message = "职位名称不能超过50位")
    private String postName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




    /**
     * -------------------------------------------------
     * 上面字段由工具自动生成，请在下面添加扩充字段
     * -------------------------------------------------
     */


}
