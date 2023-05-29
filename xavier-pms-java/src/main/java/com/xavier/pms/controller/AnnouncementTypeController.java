package com.xavier.pms.controller;

import com.xavier.pms.dto.AnnouncementTypeDto;
import com.xavier.pms.dto.AnnouncementTypeQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IAnnouncementTypeService;
import com.xavier.pms.vo.AnnouncementTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-公告类型管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("announcementType")
@RestController
@Api(tags = "公告类型管理接口")
public class AnnouncementTypeController extends CommonController {

    @Resource
    private IAnnouncementTypeService announcementTypeService;

    @ApiOperation(value = "新增公告类型", notes = "新增公告类型")
    @PutMapping("add")
    public Result add(@Validated @RequestBody AnnouncementTypeDto announcementTypeDto) {
        announcementTypeService.createAnnouncementType(announcementTypeDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑公告类型", notes = "编辑公告类型")
    @PutMapping("update")
    public Result update(@Validated @RequestBody AnnouncementTypeDto announcementTypeDto) {
        announcementTypeService.updateAnnouncementType(announcementTypeDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除公告类型", notes = "删除公告类型")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        announcementTypeService.deleteAnnouncementType(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取公告类型信息", notes = "根据公告类型ID获取公告类型信息")
    @GetMapping("get/{id}")
    public Result<AnnouncementTypeVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(announcementTypeService.getAnnouncementType(id));
    }

    @ApiOperation(value = "分页获取公告类型列表信息", notes = "分页获取公告类型列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<AnnouncementTypeVo>> queryAnnouncementType(@Validated @RequestBody AnnouncementTypeQueryDto announcementTypeQueryDto) {
        return Result.ok(announcementTypeService.queryAnnouncementType(announcementTypeQueryDto));
    }

}
