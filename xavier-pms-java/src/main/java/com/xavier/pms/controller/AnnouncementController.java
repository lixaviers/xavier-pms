package com.xavier.pms.controller;

import com.xavier.pms.dto.AnnouncementDto;
import com.xavier.pms.dto.AnnouncementQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IAnnouncementService;
import com.xavier.pms.vo.AnnouncementVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-公告管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("announcement")
@RestController
@Api(tags = "公告管理接口")
public class AnnouncementController extends CommonController {

    @Resource
    private IAnnouncementService announcementService;

    @ApiOperation(value = "新增公告", notes = "新增公告")
    @PutMapping("add")
    public Result add(@Validated @RequestBody AnnouncementDto announcementDto) {
        announcementService.createAnnouncement(announcementDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑公告", notes = "编辑公告")
    @PutMapping("update")
    public Result update(@Validated @RequestBody AnnouncementDto announcementDto) {
        announcementService.updateAnnouncement(announcementDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除公告", notes = "删除公告")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        announcementService.deleteAnnouncement(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取公告信息", notes = "根据公告ID获取公告信息")
    @GetMapping("get/{id}")
    public Result<AnnouncementVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(announcementService.getAnnouncement(id));
    }

    @ApiOperation(value = "分页获取公告列表信息", notes = "分页获取公告列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<AnnouncementVo>> queryAnnouncement(@Validated @RequestBody AnnouncementQueryDto announcementQueryDto) {
        return Result.ok(announcementService.queryAnnouncement(announcementQueryDto));
    }

}
