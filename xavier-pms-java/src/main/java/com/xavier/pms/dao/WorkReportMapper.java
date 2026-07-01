package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.WorkReportQueryDto;
import com.xavier.pms.model.WorkReport;
import com.xavier.pms.vo.ReportStatVo;
import com.xavier.pms.vo.WorkReportVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作汇报 Mapper
 */
@Mapper
public interface WorkReportMapper extends BaseMapper<WorkReport> {

    Page<WorkReportVo> queryWorkReport(@Param("dto") WorkReportQueryDto dto, IPage<?> page, @Param("loginUserId") Long loginUserId);

    WorkReportVo getWorkReportDetail(@Param("id") Long id);

    List<ReportStatVo> queryReportStat(@Param("reportType") Byte reportType,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate,
                                        @Param("deptId") Long deptId);
}
