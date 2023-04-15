package com.xavier.pms.controller;

import com.xavier.pms.dto.ApplicationGroupDto;
import com.xavier.pms.dto.ApplicationGroupQueryDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApplicationGroupService;
import com.xavier.pms.vo.ApplicationGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-应用分组管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("applicationGroup")
@RestController
@Api(tags = "应用分组管理接口")
public class ApplicationGroupController extends CommonController {

    @Resource
    private IApplicationGroupService applicationGroupService;

    @ApiOperation(value = "新增应用分组", notes = "新增应用分组")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApplicationGroupDto applicationGroupDto) {
        applicationGroupService.createApplicationGroup(applicationGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑应用分组", notes = "编辑应用分组")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApplicationGroupDto applicationGroupDto) {
        applicationGroupService.updateApplicationGroup(applicationGroupDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除应用分组", notes = "删除应用分组")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@ApiParam("id") @PathVariable Long id) {
        applicationGroupService.deleteApplicationGroup(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取应用分组信息", notes = "根据应用分组ID获取应用分组信息")
    @GetMapping("get/{id}")
    public Result<ApplicationGroupVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(applicationGroupService.getApplicationGroup(id));
    }

    @ApiOperation(value = "分页获取应用分组列表信息", notes = "分页获取应用分组列表信息")
    @PostMapping("query")
    public Result<List<ApplicationGroupVo>> queryApplicationGroup(@Validated @RequestBody ApplicationGroupQueryDto applicationGroupQueryDto) {
        return Result.ok(applicationGroupService.queryApplicationGroup(applicationGroupQueryDto));
    }

}
