package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.ReportTemplateQueryDto;
import com.xavier.pms.model.ReportTemplate;
import com.xavier.pms.vo.ReportTemplateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 汇报模板 Mapper
 */
@Mapper
public interface ReportTemplateMapper extends BaseMapper<ReportTemplate> {

    Page<ReportTemplateVo> queryReportTemplate(@Param("dto") ReportTemplateQueryDto dto, IPage<?> page);

    ReportTemplateVo getReportTemplateDetail(@Param("id") Long id);
}
