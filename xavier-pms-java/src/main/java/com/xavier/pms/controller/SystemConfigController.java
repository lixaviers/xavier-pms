package com.xavier.pms.controller;

import com.xavier.pms.dto.EmployeeCardConfigDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ISystemConfigService;
import com.xavier.pms.vo.EmployeeCardConfigVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("systemConfig")
@RestController
@Api(tags = "系统配置管理接口")
public class SystemConfigController extends CommonController {

    @Resource
    private ISystemConfigService systemConfigService;

    @ApiOperation(value = "保存个人卡片配置", notes = "保存个人卡片显示配置")
    @PostMapping("saveEmployeeCardConfig")
    public Result<Void> saveEmployeeCardConfig(@Validated @RequestBody EmployeeCardConfigDto configDto) {
        systemConfigService.saveEmployeeCardConfig(configDto);
        return Result.ok();
    }

    @ApiOperation(value = "获取个人卡片配置", notes = "获取个人卡片显示配置")
    @GetMapping("getEmployeeCardConfig")
    public Result<EmployeeCardConfigVo> getEmployeeCardConfig() {
        return Result.ok(systemConfigService.getEmployeeCardConfig());
    }

}
