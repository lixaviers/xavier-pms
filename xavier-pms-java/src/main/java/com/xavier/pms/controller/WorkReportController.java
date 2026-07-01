package com.xavier.pms.controller;

import com.xavier.pms.dto.ReportCommentAddDto;
import com.xavier.pms.dto.WorkReportAddDto;
import com.xavier.pms.dto.WorkReportQueryDto;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.Result;
import com.xavier.pms.service.IWorkReportService;
import com.xavier.pms.vo.ReportStatVo;
import com.xavier.pms.vo.WorkReportVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 工作汇报管理接口
 */
@Slf4j
@RequestMapping("workReport")
@RestController
@Tag(name = "工作汇报管理接口")
public class WorkReportController extends CommonController {

    @Resource
    private IWorkReportService workReportService;

    @PutMapping("add")
    @Operation(summary = "新增/编辑工作汇报")
    public Result<Void> add(@Valid @RequestBody WorkReportAddDto dto) {
        workReportService.saveWorkReport(dto, getLoginUser().getId());
        return Result.ok();
    }

    @PostMapping("query")
    @Operation(summary = "分页查询工作汇报")
    public Result<QueryResultVo<WorkReportVo>> query(@Valid @RequestBody WorkReportQueryDto dto) {
        return Result.ok(workReportService.queryWorkReport(dto, getLoginUser().getId()));
    }

    @GetMapping("get/{id}")
    @Operation(summary = "查询工作汇报详情")
    public Result<WorkReportVo> get(@PathVariable Long id) {
        return Result.ok(workReportService.getWorkReportDetail(id));
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除工作汇报")
    public Result<Void> delete(@RequestParam Long id) {
        workReportService.deleteWorkReport(id);
        return Result.ok();
    }

    @PutMapping("submit/{id}")
    @Operation(summary = "提交工作汇报")
    public Result<Void> submit(@PathVariable Long id) {
        workReportService.submitWorkReport(id, getLoginUser().getId());
        return Result.ok();
    }

    @PutMapping("comment")
    @Operation(summary = "添加汇报评论")
    public Result<Void> comment(@Valid @RequestBody ReportCommentAddDto dto) {
        workReportService.addComment(dto, getLoginUser().getId());
        return Result.ok();
    }

    @GetMapping("stat")
    @Operation(summary = "汇报统计")
    public Result<List<ReportStatVo>> stat(
            @RequestParam(required = false) Byte reportType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Long deptId) {
        return Result.ok(workReportService.queryReportStat(reportType, startDate, endDate, deptId));
    }
}
