package com.xavier.pms.controller;

import com.xavier.pms.result.Result;
import com.xavier.pms.service.IReportService;
import com.xavier.pms.vo.ConversionReportVo;
import com.xavier.pms.vo.EmployeeReportVo;
import com.xavier.pms.vo.ResignationReportVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理层-报表统计管理类
 *
 * @author Xavier
 */
@Slf4j
@RequestMapping("report")
@RestController
@Tag(name = "报表统计接口")
public class ReportController extends CommonController {

    @Resource
    private IReportService reportService;

    @Operation(summary = "员工信息报表", description = "获取员工人数、状态、性别、学历、政治面貌、婚姻状况、部门分布等统计数据")
    @GetMapping("employee")
    public Result<EmployeeReportVo> employeeReport() {
        return Result.ok(reportService.getEmployeeReport());
    }

    @Operation(summary = "转正统计报表", description = "获取转正率、试用期分析等统计数据")
    @GetMapping("conversion")
    public Result<ConversionReportVo> conversionReport() {
        return Result.ok(reportService.getConversionReport());
    }

    @Operation(summary = "离职统计报表", description = "获取离职率、离职原因分析、部门离职对比等统计数据")
    @GetMapping("resignation")
    public Result<ResignationReportVo> resignationReport() {
        return Result.ok(reportService.getResignationReport());
    }

    @Operation(summary = "导出员工信息Excel", description = "导出员工信息报表Excel文件")
    @GetMapping("employee/export")
    public void exportEmployeeExcel(HttpServletResponse response) {
        reportService.exportEmployeeExcel(response);
    }

    @Operation(summary = "导出转正统计Excel", description = "导出转正统计报表Excel文件")
    @GetMapping("conversion/export")
    public void exportConversionExcel(HttpServletResponse response) {
        reportService.exportConversionExcel(response);
    }

    @Operation(summary = "导出离职统计Excel", description = "导出离职统计报表Excel文件")
    @GetMapping("resignation/export")
    public void exportResignationExcel(HttpServletResponse response) {
        reportService.exportResignationExcel(response);
    }

}
