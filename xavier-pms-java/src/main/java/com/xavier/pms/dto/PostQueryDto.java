package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("职位查询入参")
public class PostQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("职位名称")
    private String postName;

    @ApiModelProperty("创建时间起")
    private LocalDateTime createTimeFrom;

    @ApiModelProperty("创建时间止")
    private LocalDateTime createTimeTo;

}
