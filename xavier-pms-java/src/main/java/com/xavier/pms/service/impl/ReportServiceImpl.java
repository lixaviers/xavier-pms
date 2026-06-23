package com.xavier.pms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.xavier.pms.dao.ConversionExportDto;
import com.xavier.pms.dao.EmployeeExportDto;
import com.xavier.pms.dao.ReportMapper;
import com.xavier.pms.dao.ResignationExportDto;
import com.xavier.pms.service.IReportService;
import com.xavier.pms.vo.ConversionReportVo;
import com.xavier.pms.vo.EmployeeReportVo;
import com.xavier.pms.vo.ResignationReportVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 业务逻辑层-报表统计实现类
 *
 * @author Xavier
 */
@Slf4j
@Service("reportService")
public class ReportServiceImpl implements IReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public EmployeeReportVo getEmployeeReport() {
        EmployeeReportVo vo = new EmployeeReportVo();

        // 员工状态分布
        List<EmployeeReportVo.DistributionItem> statusList = reportMapper.countByUserStatus();
        long totalCount = 0, normalCount = 0, pendingCount = 0, leftCount = 0;
        for (EmployeeReportVo.DistributionItem item : statusList) {
            totalCount += item.getCount();
            switch (item.getName()) {
                case "正常" -> normalCount = item.getCount();
                case "待审批" -> pendingCount = item.getCount();
                case "已离职" -> leftCount = item.getCount();
            }
        }
        vo.setTotalCount(totalCount);
        vo.setNormalCount(normalCount);
        vo.setPendingCount(pendingCount);
        vo.setLeftCount(leftCount);

        // 各维度分布
        vo.setGenderDistribution(reportMapper.countByGender());
        vo.setDegreeDistribution(reportMapper.countByDegree());
        vo.setPoliticalDistribution(reportMapper.countByPoliticalStatus());
        vo.setMaritalDistribution(reportMapper.countByMaritalStatus());
        vo.setDepartmentDistribution(reportMapper.countByDepartment());

        return vo;
    }

    @Override
    public ConversionReportVo getConversionReport() {
        ConversionReportVo vo = new ConversionReportVo();

        Long probationCount = reportMapper.countProbation();
        Long convertedCount = reportMapper.countConverted();
        vo.setProbationCount(probationCount);
        vo.setConvertedCount(convertedCount);
        vo.setThisMonthConversionCount(reportMapper.countThisMonthConversion());

        // 计算转正率
        long total = probationCount + convertedCount;
        if (total > 0) {
            vo.setConversionRate(Math.round(convertedCount * 1000.0 / total) / 10.0);
        } else {
            vo.setConversionRate(0.0);
        }

        vo.setMonthlyConversion(reportMapper.countMonthlyConversion());
        vo.setProbationPeriodDistribution(reportMapper.countByProbationPeriod());

        return vo;
    }

    @Override
    public ResignationReportVo getResignationReport() {
        ResignationReportVo vo = new ResignationReportVo();

        Long resignationTotal = reportMapper.countResignation();
        // 获取全部员工数（含已离职）计算离职率
        Long allEmployeeCount = reportMapper.countByUserStatus().stream()
                .mapToLong(EmployeeReportVo.DistributionItem::getCount).sum();

        vo.setTotalCount(resignationTotal);
        vo.setThisMonthCount(reportMapper.countThisMonthResignation());

        // 离职率 = 离职人数 / 总人数 * 100
        if (allEmployeeCount > 0) {
            vo.setResignationRate(Math.round(resignationTotal * 1000.0 / allEmployeeCount) / 10.0);
        } else {
            vo.setResignationRate(0.0);
        }

        vo.setReasonDistribution(reportMapper.countByResignationType());
        vo.setDepartmentResignation(reportMapper.countDepartmentResignation());
        vo.setMonthlyTrend(reportMapper.countMonthlyResignation());

        return vo;
    }

    @Override
    public void exportEmployeeExcel(HttpServletResponse response) {
        try {
            setExcelResponseHeaders(response, "员工信息报表");
            List<EmployeeExportDto> data = reportMapper.queryAllEmployees();
            EasyExcel.write(response.getOutputStream(), EmployeeExportDto.class)
                    .sheet("员工信息")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出员工信息Excel失败", e);
        }
    }

    @Override
    public void exportConversionExcel(HttpServletResponse response) {
        try {
            setExcelResponseHeaders(response, "转正统计报表");
            List<ConversionExportDto> data = reportMapper.queryConversionRecords();
            EasyExcel.write(response.getOutputStream(), ConversionExportDto.class)
                    .sheet("转正统计")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出转正统计Excel失败", e);
        }
    }

    @Override
    public void exportResignationExcel(HttpServletResponse response) {
        try {
            setExcelResponseHeaders(response, "离职统计报表");
            List<ResignationExportDto> data = reportMapper.queryResignationRecords();
            EasyExcel.write(response.getOutputStream(), ResignationExportDto.class)
                    .sheet("离职统计")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出离职统计Excel失败", e);
        }
    }

    /**
     * 设置Excel响应头
     */
    private void setExcelResponseHeaders(HttpServletResponse response, String fileName) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ".xlsx");
    }
}
