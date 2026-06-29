package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "日程出参")
public class ScheduleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "日程标题")
    private String title;

    @Schema(description = "日程类型: 1-个人日程 2-会议")
    private Byte scheduleType;

    @Schema(description = "日程类型描述")
    private String scheduleTypeDesc;

    @Schema(description = "创建人id")
    private Long userId;

    @Schema(description = "创建人姓名")
    private String nickName;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "是否全天")
    private Boolean isAllDay;

    @Schema(description = "地点")
    private String location;

    @Schema(description = "会议室id")
    private Long meetingRoomId;

    @Schema(description = "会议室名称")
    private String meetingRoomName;

    @Schema(description = "提醒方式")
    private Byte remindType;

    @Schema(description = "提醒方式描述")
    private String remindTypeDesc;

    @Schema(description = "颜色标签")
    private String color;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "是否公开")
    private Boolean isPublic;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "参与人列表")
    private List<ParticipantItem> participants;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "参与人项")
    public static class ParticipantItem implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "id")
        private Long id;

        @Schema(description = "用户id")
        private Long userId;

        @Schema(description = "姓名")
        private String nickName;

        @Schema(description = "接受状态: 0-待确认 1-已接受 2-已拒绝")
        private Byte acceptStatus;

        @Schema(description = "接受状态描述")
        private String acceptStatusDesc;

    }

}
