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


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批单审批入参")
public class AuditFormAuditDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 是否通过
     */
    @ApiModelProperty(value = "是否通过", required = true)
    @NotNull(message = "是否通过不能为空")
    private Boolean pass;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    @NotNull(message = "备注不能为空")
    @NotEmpty(message = "备注不能为空")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;


}
