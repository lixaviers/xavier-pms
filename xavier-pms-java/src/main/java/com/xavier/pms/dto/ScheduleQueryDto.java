package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "日程查询入参")
public class ScheduleQueryDto extends QueryDto {

    private static final long serialVersionUID = 1L;

    @Schema(description = "日程标题")
    private String title;

    @Schema(description = "日程类型: 1-个人日程 2-会议")
    private Byte scheduleType;

    @Schema(description = "开始时间（范围查询-起）")
    private String startTimeBegin;

    @Schema(description = "开始时间（范围查询-止）")
    private String startTimeEnd;

    @Schema(description = "是否只查我的日程")
    private Boolean onlyMine;

}
