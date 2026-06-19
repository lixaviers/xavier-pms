package com.xavier.pms.controller;

import com.xavier.pms.dto.EmployeeCardConfigDto;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.ISystemConfigService;
import com.xavier.pms.vo.EmployeeCardConfigVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@Slf4j
@RequestMapping("systemConfig")
@RestController
@Tag(name = "系统配置管理接口")
public class SystemConfigController extends CommonController {

    @Resource
    private ISystemConfigService systemConfigService;

    @Operation(summary = "保存个人卡片配置", description = "保存个人卡片显示配置")
    @PostMapping("saveEmployeeCardConfig")
    public Result<Void> saveEmployeeCardConfig(@Validated @RequestBody EmployeeCardConfigDto configDto) {
        systemConfigService.saveEmployeeCardConfig(configDto);
        return Result.ok();
    }

    @Operation(summary = "获取个人卡片配置", description = "获取个人卡片显示配置")
    @GetMapping("getEmployeeCardConfig")
    public Result<EmployeeCardConfigVo> getEmployeeCardConfig() {
        return Result.ok(systemConfigService.getEmployeeCardConfig());
    }

}
