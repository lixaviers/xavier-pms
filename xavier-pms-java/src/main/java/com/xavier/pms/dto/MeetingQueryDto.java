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
@ApiModel("会议室查询入参")
public class MeetingQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("会议室名称")
    private String meetingName;

    @ApiModelProperty("位置")
    private String position;

    @ApiModelProperty("是否启用")
    private Boolean isEnable;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("创建时间起")
    private LocalDateTime createTimeFrom;

    @ApiModelProperty("创建时间止")
    private LocalDateTime createTimeTo;


}
