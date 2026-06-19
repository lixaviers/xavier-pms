package com.xavier.pms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xavier.pms.dto.MeetingDto;
import com.xavier.pms.dto.MeetingQueryDto;
import com.xavier.pms.vo.MeetingVo;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IMeetingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-会议室管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("meeting")
@RestController
@Tag(name = "会议室管理接口")
public class MeetingController extends CommonController {

    @Resource
    private IMeetingService meetingService;

    @Operation(summary = "新增会议室", description = "新增会议室")
    @PutMapping("add")
    public Result add(@Validated @RequestBody MeetingDto meetingDto) {
        meetingService.createMeeting(meetingDto);
        return Result.ok();
    }

    @Operation(summary = "编辑会议室", description = "编辑会议室")
    @PutMapping("update")
    public Result update(@Validated @RequestBody MeetingDto meetingDto) {
        meetingService.updateMeeting(meetingDto);
        return Result.ok();
    }

    @Operation(summary = "删除会议室", description = "删除会议室")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        meetingService.deleteMeeting(idList);
        return Result.ok();
    }

    @Operation(summary = "获取会议室信息", description = "根据会议室ID获取会议室信息")
    @GetMapping("get/{id}")
    public Result<MeetingVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(meetingService.getMeeting(id));
    }

    @Operation(summary = "分页获取会议室列表信息", description = "分页获取会议室列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<MeetingVo>> queryMeeting(@Validated @RequestBody MeetingQueryDto meetingQueryDto) {
        return Result.ok(meetingService.queryMeeting(meetingQueryDto));
    }

}
