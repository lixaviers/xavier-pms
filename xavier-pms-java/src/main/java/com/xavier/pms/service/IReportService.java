package com.xavier.pms.service;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 业务逻辑层-报表统计接口类
 *
 * @author Xavier
 */
public interface IReportService {

    /**
     * 获取员工信息报表
     */
    com.xavier.pms.vo.EmployeeReportVo getEmployeeReport();

    /**
     * 获取转正统计报表
     */
    com.xavier.pms.vo.ConversionReportVo getConversionReport();

    /**
     * 获取离职统计报表
     */
    com.xavier.pms.vo.ResignationReportVo getResignationReport();

    /**
     * 导出员工信息Excel
     */
    void exportEmployeeExcel(HttpServletResponse response);

    /**
     * 导出转正统计Excel
     */
    void exportConversionExcel(HttpServletResponse response);

    /**
     * 导出离职统计Excel
     */
    void exportResignationExcel(HttpServletResponse response);
}
