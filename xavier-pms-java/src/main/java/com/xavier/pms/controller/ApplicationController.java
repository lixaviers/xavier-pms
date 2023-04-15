package com.xavier.pms.controller;

import com.xavier.pms.dto.ApplicationDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IApplicationService;
import com.xavier.pms.vo.ApplicationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求处理层-应用管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("application")
@RestController
@Api(tags = "应用管理接口")
public class ApplicationController extends CommonController {

    @Resource
    private IApplicationService applicationService;

    @ApiOperation(value = "新增应用", notes = "新增应用")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ApplicationDto applicationDto) {
        applicationService.createApplication(applicationDto);
        return Result.ok();
    }

    @ApiOperation(value = "编辑应用", notes = "编辑应用")
    @PutMapping("update")
    public Result update(@Validated @RequestBody ApplicationDto applicationDto) {
        applicationService.updateApplication(applicationDto);
        return Result.ok();
    }

    @ApiOperation(value = "删除应用", notes = "删除应用")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@ApiParam("id") @PathVariable Long id) {
        applicationService.deleteApplication(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取应用信息", notes = "根据应用ID获取应用信息")
    @GetMapping("get/{id}")
    public Result<ApplicationVo> get(@ApiParam("id") @PathVariable Long id) {
        return Result.ok(applicationService.getApplication(id));
    }

}
