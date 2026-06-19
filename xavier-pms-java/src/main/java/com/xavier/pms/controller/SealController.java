package com.xavier.pms.controller;

import com.xavier.pms.dto.SealDto;
import com.xavier.pms.dto.SealQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ISealService;
import com.xavier.pms.vo.SealVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-印章管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("seal")
@RestController
@Tag(name = "印章管理接口")
public class SealController extends CommonController {

    @Resource
    private ISealService sealService;

    @Operation(summary = "新增印章", description = "新增印章")
    @PutMapping("add")
    public Result add(@Validated @RequestBody SealDto sealDto) {
        sealService.createSeal(sealDto);
        return Result.ok();
    }

    @Operation(summary = "编辑印章", description = "编辑印章")
    @PutMapping("update")
    public Result update(@Validated @RequestBody SealDto sealDto) {
        sealService.updateSeal(sealDto);
        return Result.ok();
    }

    @Operation(summary = "删除印章", description = "删除印章")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        sealService.deleteSeal(idList);
        return Result.ok();
    }

    @Operation(summary = "获取印章信息", description = "根据印章ID获取印章信息")
    @GetMapping("get/{id}")
    public Result<SealVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(sealService.getSeal(id));
    }

    @Operation(summary = "分页获取印章列表信息", description = "分页获取印章列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<SealVo>> querySeal(@Validated @RequestBody SealQueryDto sealQueryDto) {
        return Result.ok(sealService.querySeal(sealQueryDto));
    }

}
