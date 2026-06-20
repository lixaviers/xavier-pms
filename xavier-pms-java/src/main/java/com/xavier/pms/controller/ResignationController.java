package com.xavier.pms.controller;

import com.xavier.pms.dto.ResignationAddDto;
import com.xavier.pms.dto.ResignationQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IResignationService;
import com.xavier.pms.vo.ResignationVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 请求处理层-离职管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("resignation")
@RestController
@Tag(name = "离职管理接口")
public class ResignationController extends CommonController {

    @Resource
    private IResignationService resignationService;

    @Operation(summary = "员工离职", description = "办理员工离职，同时将员工状态改为已离职")
    @PutMapping("add")
    public Result add(@Validated @RequestBody ResignationAddDto dto) {
        resignationService.resign(dto);
        return Result.ok();
    }

    @Operation(summary = "分页查询离职记录", description = "分页查询离职记录列表")
    @PostMapping("query")
    public Result<QueryResultVo<ResignationVo>> query(@Validated @RequestBody ResignationQueryDto dto) {
        return Result.ok(resignationService.queryResignation(dto));
    }

    @Operation(summary = "查询离职详情", description = "根据id查询离职记录详情")
    @GetMapping("get/{id}")
    public Result<ResignationVo> get(@Parameter(description = "id") @PathVariable Long id) {
        return Result.ok(resignationService.getResignationDetail(id));
    }

}
