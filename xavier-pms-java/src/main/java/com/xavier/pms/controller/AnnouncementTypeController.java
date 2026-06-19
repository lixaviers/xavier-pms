package com.xavier.pms.controller;

import com.xavier.pms.dto.AnnouncementTypeDto;
import com.xavier.pms.dto.AnnouncementTypeQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IAnnouncementTypeService;
import com.xavier.pms.vo.AnnouncementTypeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
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
@Tag(name = "公告类型管理接口")
public class AnnouncementTypeController extends CommonController {

    @Resource
    private IAnnouncementTypeService announcementTypeService;

    @Operation(summary = "新增公告类型", description = "新增公告类型")
    @PutMapping("add")
    public Result add(@Validated @RequestBody AnnouncementTypeDto announcementTypeDto) {
        announcementTypeService.createAnnouncementType(announcementTypeDto);
        return Result.ok();
    }

    @Operation(summary = "编辑公告类型", description = "编辑公告类型")
    @PutMapping("update")
    public Result update(@Validated @RequestBody AnnouncementTypeDto announcementTypeDto) {
        announcementTypeService.updateAnnouncementType(announcementTypeDto);
        return Result.ok();
    }

    @Operation(summary = "删除公告类型", description = "删除公告类型")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        announcementTypeService.deleteAnnouncementType(idList);
        return Result.ok();
    }

    @Operation(summary = "获取公告类型信息", description = "根据公告类型ID获取公告类型信息")
    @GetMapping("get/{id}")
    public Result<AnnouncementTypeVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(announcementTypeService.getAnnouncementType(id));
    }

    @Operation(summary = "分页获取公告类型列表信息", description = "分页获取公告类型列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<AnnouncementTypeVo>> queryAnnouncementType(@Validated @RequestBody AnnouncementTypeQueryDto announcementTypeQueryDto) {
        return Result.ok(announcementTypeService.queryAnnouncementType(announcementTypeQueryDto));
    }

}
