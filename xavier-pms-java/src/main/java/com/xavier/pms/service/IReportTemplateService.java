package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ReportTemplateAddDto;
import com.xavier.pms.dto.ReportTemplateQueryDto;
import com.xavier.pms.model.ReportTemplate;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.ReportTemplateVo;

/**
 * 汇报模板 Service
 */
public interface IReportTemplateService extends IService<ReportTemplate> {

    void saveReportTemplate(ReportTemplateAddDto dto, Long userId);

    QueryResultVo<ReportTemplateVo> queryReportTemplate(ReportTemplateQueryDto dto);

    ReportTemplateVo getReportTemplateDetail(Long id);

    void deleteReportTemplate(Long id);
}
