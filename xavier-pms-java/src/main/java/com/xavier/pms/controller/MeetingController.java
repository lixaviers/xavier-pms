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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
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
@Api(tags = "会议室管理接口")
public class MeetingController extends CommonController {

    @Resource
    private IMeetingService meetingService;

    @ApiOperation(value = "新增会议室", notes = "新增会议室")
    @PutMapping("add")
    public Result add(@Validated @RequestBody MeetingDto meetingDto) {
        meetingService.createMeeting(meetingDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑会议室", notes = "编辑会议室")
    @PutMapping("update")
    public Result update(@Validated @RequestBody MeetingDto meetingDto) {
        meetingService.updateMeeting(meetingDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除会议室", notes = "删除会议室")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        meetingService.deleteMeeting(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取会议室信息", notes = "根据会议室ID获取会议室信息")
    @GetMapping("get/{id}")
    public Result<MeetingVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(meetingService.getMeeting(id));
    }

    @ApiOperation(value = "分页获取会议室列表信息", notes = "分页获取会议室列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<MeetingVo>> queryMeeting(@Validated @RequestBody MeetingQueryDto meetingQueryDto) {
        return Result.ok(meetingService.queryMeeting(meetingQueryDto));
    }

}
