package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.ReportTemplateMapper;
import com.xavier.pms.dto.ReportTemplateAddDto;
import com.xavier.pms.dto.ReportTemplateQueryDto;
import com.xavier.pms.enums.ReportTypeEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.ReportTemplate;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IReportTemplateService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ReportTemplateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 汇报模板 Service 实现
 */
@Slf4j
@Service("reportTemplateService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ReportTemplateServiceImpl extends ServiceImpl<ReportTemplateMapper, ReportTemplate> implements IReportTemplateService {

    @Override
    public void saveReportTemplate(ReportTemplateAddDto dto, Long userId) {
        // 校验汇报类型
        if (!ReportTypeEnum.exists(dto.getReportType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "汇报类型");
        }

        ReportTemplate entity;
        if (dto.getId() != null) {
            // 编辑
            entity = this.getById(dto.getId());
            if (entity == null) {
                throw new ServiceException(ResultCode.DATA_NOT_EXIST, "汇报模板");
            }
            BeanUtil.beanCopy(dto, entity);
        } else {
            // 新增
            entity = new ReportTemplate();
            BeanUtil.beanCopy(dto, entity);
            entity.setCreateUserId(userId);
        }
        this.saveOrUpdate(entity);
    }

    @Override
    public QueryResultVo<ReportTemplateVo> queryReportTemplate(ReportTemplateQueryDto dto) {
        IPage<ReportTemplateVo> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        Page<ReportTemplateVo> result = baseMapper.queryReportTemplate(dto, page);

        QueryResultVo<ReportTemplateVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, ReportTemplateVo.class);
        queryResultVo.setRecords(result.getRecords());

        // 填充枚举描述
        queryResultVo.getRecords().forEach(vo -> {
            vo.setReportTypeDesc(ReportTypeEnum.getDescByValue(vo.getReportType()));
        });

        return queryResultVo;
    }

    @Override
    public ReportTemplateVo getReportTemplateDetail(Long id) {
        ReportTemplateVo vo = baseMapper.getReportTemplateDetail(id);
        if (vo == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "汇报模板");
        }
        vo.setReportTypeDesc(ReportTypeEnum.getDescByValue(vo.getReportType()));
        return vo;
    }

    @Override
    public void deleteReportTemplate(Long id) {
        ReportTemplate entity = this.getById(id);
        if (entity == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "汇报模板");
        }
        this.removeById(id);
    }
}
