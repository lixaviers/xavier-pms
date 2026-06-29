package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "日程新增/编辑入参")
public class ScheduleAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id（编辑时传入）")
    private Long id;

    @Schema(description = "日程标题", required = true)
    @NotBlank(message = "日程标题不能为空")
    @Size(max = 200, message = "日程标题不能超过200位")
    private String title;

    @Schema(description = "日程类型: 1-个人日程 2-会议", required = true)
    @NotNull(message = "日程类型不能为空")
    private Byte scheduleType;

    @Schema(description = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "是否全天: 0-否 1-是")
    private Boolean isAllDay;

    @Schema(description = "地点")
    @Size(max = 200, message = "地点不能超过200位")
    private String location;

    @Schema(description = "会议室id")
    private Long meetingRoomId;

    @Schema(description = "提醒方式: 0-不提醒 1-提前5分钟 2-提前15分钟 3-提前30分钟 4-提前1小时 5-提前1天")
    private Byte remindType;

    @Schema(description = "颜色标签")
    private String color;

    @Schema(description = "描述")
    @Size(max = 2000, message = "描述不能超过2000位")
    private String description;

    @Schema(description = "是否公开: 0-仅自己可见 1-团队可见")
    private Boolean isPublic;

    @Schema(description = "参与人id列表（会议类型时使用）")
    private List<Long> participantIds;

}
