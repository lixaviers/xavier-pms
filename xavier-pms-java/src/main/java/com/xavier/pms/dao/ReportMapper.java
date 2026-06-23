package com.xavier.pms.dao;

import com.xavier.pms.vo.ConversionReportVo;
import com.xavier.pms.vo.EmployeeReportVo;
import com.xavier.pms.vo.ResignationReportVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报表统计数据访问层
 *
 * @author Xavier
 */
@Mapper
public interface ReportMapper {

    // ===== 员工信息报表 =====

    /** 员工总数（按状态分组） */
    List<EmployeeReportVo.DistributionItem> countByUserStatus();

    /** 性别分布 */
    List<EmployeeReportVo.DistributionItem> countByGender();

    /** 学历分布 */
    List<EmployeeReportVo.DistributionItem> countByDegree();

    /** 政治面貌分布 */
    List<EmployeeReportVo.DistributionItem> countByPoliticalStatus();

    /** 婚姻状况分布 */
    List<EmployeeReportVo.DistributionItem> countByMaritalStatus();

    /** 部门分布 */
    List<EmployeeReportVo.DistributionItem> countByDepartment();

    // ===== 转正统计报表 =====

    /** 试用期员工数（正常状态且预计转正日期在未来） */
    Long countProbation();

    /** 已转正员工数（正常状态且预计转正日期在过去） */
    Long countConverted();

    /** 本月应转正人数 */
    Long countThisMonthConversion();

    /** 按月统计转正人数（近12个月） */
    List<ConversionReportVo.MonthlyItem> countMonthlyConversion();

    /** 试用期时长分布 */
    List<ConversionReportVo.ProbationPeriodItem> countByProbationPeriod();

    // ===== 离职统计报表 =====

    /** 离职总人数 */
    Long countResignation();

    /** 本月离职人数 */
    Long countThisMonthResignation();

    /** 离职原因分布（按离职类型分组） */
    List<ResignationReportVo.ReasonItem> countByResignationType();

    /** 部门离职对比 */
    List<ResignationReportVo.DepartmentResignationItem> countDepartmentResignation();

    /** 月度离职趋势（近12个月） */
    List<ResignationReportVo.MonthlyItem> countMonthlyResignation();

    // ===== Excel 导出查询 =====

    /** 查询所有员工信息（导出用） */
    List<EmployeeExportDto> queryAllEmployees();

    /** 查询转正记录（导出用） */
    List<ConversionExportDto> queryConversionRecords();

    /** 查询离职记录（导出用） */
    List<ResignationExportDto> queryResignationRecords();
}
