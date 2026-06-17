package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.OperateLogMapper;
import com.xavier.pms.dto.OperateLogDto;
import com.xavier.pms.dto.OperateLogQueryDto;
import com.xavier.pms.model.OperateLog;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.service.IOperateLogService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.OperateLogVo;
import org.springframework.stereotype.Service;

@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {

    @Override
    public void addOperateLog(OperateLogDto dto) {
        OperateLog bean = BeanUtil.beanCopy(dto, OperateLog.class);
        bean.setJavaMethodArgs(StrUtil.maxLength(bean.getJavaMethodArgs(), 3997));
        bean.setResultData(StrUtil.maxLength(bean.getResultData(), 3997));
        bean.setResultMsg(StrUtil.maxLength(bean.getResultMsg(), 490));
        bean.setId(null);
        super.save(bean);
    }

    @Override
    public QueryResultVo<OperateLogVo> queryOperateLog(OperateLogQueryDto queryDTO) {
        Page<OperateLog> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());

        LambdaQueryWrapper<OperateLog> wrapper = OperateLog.gw();

        if (StrUtil.isNotBlank(queryDTO.getUserIp())) {
            wrapper.eq(OperateLog::getUserIp, queryDTO.getUserIp());
        }
        if (StrUtil.isNotBlank(queryDTO.getOperateName())) {
            wrapper.like(OperateLog::getOperateName, queryDTO.getOperateName());
        }
        if (StrUtil.isNotBlank(queryDTO.getRequestMethod())) {
            wrapper.eq(OperateLog::getRequestMethod, queryDTO.getRequestMethod());
        }
        if (StrUtil.isNotBlank(queryDTO.getRequestUrl())) {
            wrapper.like(OperateLog::getRequestUrl, queryDTO.getRequestUrl());
        }
        if (StrUtil.isNotBlank(queryDTO.getTraceId())) {
            wrapper.eq(OperateLog::getTraceId, queryDTO.getTraceId());
        }
        if (queryDTO.getResultCode() != null) {
            wrapper.eq(OperateLog::getResultCode, queryDTO.getResultCode());
        }
        if (queryDTO.getCreateTimeFrom() != null) {
            wrapper.ge(OperateLog::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (queryDTO.getCreateTimeTo() != null) {
            wrapper.le(OperateLog::getCreateTime, queryDTO.getCreateTimeTo());
        }

        wrapper.orderByDesc(OperateLog::getCreateTime);

        Page<OperateLog> result = super.page(page, wrapper);
        QueryResultVo<OperateLogVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, OperateLogVo.class);
        queryResultVo.setRecords(BeanUtil.beanCopy(result.getRecords(), OperateLogVo.class));
        return queryResultVo;
    }

}
