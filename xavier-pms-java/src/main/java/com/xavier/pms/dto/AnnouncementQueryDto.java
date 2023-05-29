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
@ApiModel("公告查询入参")
public class AnnouncementQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告类型id")
    private Long announcementTypeId;

    @ApiModelProperty("标题")
    private String title;

}
