package com.xavier.pms.controller;

import com.xavier.pms.dto.ScheduleAddDto;
import com.xavier.pms.dto.ScheduleQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IScheduleService;
import com.xavier.pms.vo.ScheduleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 请求处理层-日程管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("schedule")
@RestController
@Tag(name = "日程管理接口")
public class ScheduleController extends CommonController {

    @Resource
    private IScheduleService scheduleService;

    @Operation(summary = "新增/编辑日程", description = "创建或编辑日程，会议类型可指定参与人")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ScheduleAddDto dto) {
        scheduleService.saveSchedule(dto, getLoginUser().getId());
        return Result.ok();
    }

    @Operation(summary = "分页查询日程", description = "分页查询日程列表")
    @PostMapping("query")
    public Result<QueryResultVo<ScheduleVo>> query(@Validated @RequestBody ScheduleQueryDto dto) {
        return Result.ok(scheduleService.querySchedule(dto, getLoginUser().getId()));
    }

    @Operation(summary = "查询日程详情", description = "根据id查询日程详情")
    @GetMapping("get/{id}")
    public Result<ScheduleVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(scheduleService.getScheduleDetail(id));
    }

    @Operation(summary = "删除日程", description = "根据id删除日程")
    @DeleteMapping("delete")
    public Result delete(@Parameter(description = "id") @RequestParam Long id) {
        scheduleService.deleteSchedule(id, getLoginUser().getId());
        return Result.ok();
    }

    @Operation(summary = "日历视图查询", description = "按时间范围查询日历视图日程")
    @GetMapping("calendar")
    public Result<List<ScheduleVo>> calendar(
            @Parameter(description = "开始时间") @RequestParam String startTime,
            @Parameter(description = "结束时间") @RequestParam String endTime) {
        LocalDateTime start = parseDateTime(startTime);
        LocalDateTime end = parseDateTime(endTime);
        return Result.ok(scheduleService.queryScheduleByTimeRange(start, end, getLoginUser().getId()));
    }

    @Operation(summary = "接受/拒绝会议", description = "参与人接受或拒绝会议邀请")
    @PutMapping("accept")
    public Result accept(
            @Parameter(description = "日程id") @RequestParam Long scheduleId,
            @Parameter(description = "接受状态: 1-已接受 2-已拒绝") @RequestParam Byte acceptStatus) {
        scheduleService.acceptSchedule(scheduleId, getLoginUser().getId(), acceptStatus);
        return Result.ok();
    }

    @Operation(summary = "今日待提醒日程", description = "查询今日有提醒的日程列表")
    @GetMapping("todayRemind")
    public Result<List<ScheduleVo>> todayRemind() {
        return Result.ok(scheduleService.queryTodayRemindSchedules(getLoginUser().getId()));
    }

    @Operation(summary = "即将到来的日程", description = "查询即将到来的日程列表（首页日程提醒卡片）")
    @GetMapping("upcoming")
    public Result<List<ScheduleVo>> upcoming() {
        return Result.ok(scheduleService.queryUpcomingSchedules(getLoginUser().getId()));
    }

    /**
     * 兼容多种时间格式解析：
     * - 2026-06-01 00:00:00+08:00（空格分隔+时区偏移）
     * - 2026-06-01T00:00:00+08:00（ISO带时区）
     * - 2026-06-01T00:00:00（ISO无时区）
     * - 2026-06-01 00:00:00（常规格式）
     * - 2026-06-01（仅日期）
     */
    private LocalDateTime parseDateTime(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        text = text.trim();
        // 去掉尾部时区偏移（+08:00、+0800、Z），统一成无时区本地时间
        String cleaned = text.replaceFirst("[+\\-]\\d{2}:?\\d{2}$", "").replaceFirst("Z$", "");
        // T 替换为空格，统一成 "yyyy-MM-dd HH:mm:ss" 格式
        cleaned = cleaned.replace('T', ' ');
        // 仅日期
        if (cleaned.length() == 10) {
            return LocalDate.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        }
        return LocalDateTime.parse(cleaned, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
