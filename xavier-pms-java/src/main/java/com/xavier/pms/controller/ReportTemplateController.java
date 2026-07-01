package com.xavier.pms.controller;

import com.xavier.pms.dto.ReportTemplateAddDto;
import com.xavier.pms.dto.ReportTemplateQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IReportTemplateService;
import com.xavier.pms.vo.ReportTemplateVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 汇报模板管理接口
 */
@Slf4j
@RequestMapping("reportTemplate")
@RestController
@Tag(name = "汇报模板管理接口")
public class ReportTemplateController extends CommonController {

    @Resource
    private IReportTemplateService reportTemplateService;

    @PutMapping("add")
    @Operation(summary = "新增/编辑汇报模板")
    public Result<Void> add(@Valid @RequestBody ReportTemplateAddDto dto) {
        reportTemplateService.saveReportTemplate(dto, getLoginUser().getId());
        return Result.ok();
    }

    @PostMapping("query")
    @Operation(summary = "分页查询汇报模板")
    public Result<QueryResultVo<ReportTemplateVo>> query(@Valid @RequestBody ReportTemplateQueryDto dto) {
        return Result.ok(reportTemplateService.queryReportTemplate(dto));
    }

    @GetMapping("get/{id}")
    @Operation(summary = "查询汇报模板详情")
    public Result<ReportTemplateVo> get(@PathVariable Long id) {
        return Result.ok(reportTemplateService.getReportTemplateDetail(id));
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除汇报模板")
    public Result<Void> delete(@RequestParam Long id) {
        reportTemplateService.deleteReportTemplate(id);
        return Result.ok();
    }
}
