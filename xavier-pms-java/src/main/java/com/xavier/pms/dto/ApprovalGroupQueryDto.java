package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批分组查询入参")
public class ApprovalGroupQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty(value = "关键字", hidden = true)
    private Byte appStatus;


}
