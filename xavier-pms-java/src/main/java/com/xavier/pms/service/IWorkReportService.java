package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ReportCommentAddDto;
import com.xavier.pms.dto.WorkReportAddDto;
import com.xavier.pms.dto.WorkReportQueryDto;
import com.xavier.pms.model.WorkReport;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.ReportStatVo;
import com.xavier.pms.vo.WorkReportVo;

import java.util.List;

/**
 * 工作汇报 Service
 */
public interface IWorkReportService extends IService<WorkReport> {

    void saveWorkReport(WorkReportAddDto dto, Long userId);

    QueryResultVo<WorkReportVo> queryWorkReport(WorkReportQueryDto dto, Long loginUserId);

    WorkReportVo getWorkReportDetail(Long id);

    void deleteWorkReport(Long id);

    void submitWorkReport(Long id, Long userId);

    void addComment(ReportCommentAddDto dto, Long userId);

    List<ReportStatVo> queryReportStat(Byte reportType, String startDate, String endDate, Long deptId);
}
