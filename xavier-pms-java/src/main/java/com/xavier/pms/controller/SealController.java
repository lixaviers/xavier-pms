package com.xavier.pms.controller;

import com.xavier.pms.dto.SealDto;
import com.xavier.pms.dto.SealQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ISealService;
import com.xavier.pms.vo.SealVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "印章管理接口")
public class SealController extends CommonController {

    @Resource
    private ISealService sealService;

    @ApiOperation(value = "新增印章", notes = "新增印章")
    @PutMapping("add")
    public Result add(@Validated @RequestBody SealDto sealDto) {
        sealService.createSeal(sealDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑印章", notes = "编辑印章")
    @PutMapping("update")
    public Result update(@Validated @RequestBody SealDto sealDto) {
        sealService.updateSeal(sealDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除印章", notes = "删除印章")
    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        sealService.deleteSeal(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取印章信息", notes = "根据印章ID获取印章信息")
    @GetMapping("get/{id}")
    public Result<SealVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(sealService.getSeal(id));
    }

    @ApiOperation(value = "分页获取印章列表信息", notes = "分页获取印章列表信息")
    @PostMapping("query")
    public Result<QueryResultVo<SealVo>> querySeal(@Validated @RequestBody SealQueryDto sealQueryDto) {
        return Result.ok(sealService.querySeal(sealQueryDto));
    }

}
