package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.ReportCommentMapper;
import com.xavier.pms.dao.WorkReportMapper;
import com.xavier.pms.dto.ReportCommentAddDto;
import com.xavier.pms.dto.WorkReportAddDto;
import com.xavier.pms.dto.WorkReportQueryDto;
import com.xavier.pms.enums.ReportTypeEnum;
import com.xavier.pms.enums.WorkReportStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.ReportComment;
import com.xavier.pms.model.WorkReport;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IWorkReportService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ReportStatVo;
import com.xavier.pms.vo.WorkReportVo;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.List;

/**
 * 工作汇报 Service 实现
 */
@Slf4j
@Service("workReportService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class WorkReportServiceImpl extends ServiceImpl<WorkReportMapper, WorkReport> implements IWorkReportService {

    @Resource
    private ReportCommentMapper reportCommentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWorkReport(WorkReportAddDto dto, Long userId) {
        // 校验汇报类型
        if (!ReportTypeEnum.exists(dto.getReportType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "汇报类型");
        }

        WorkReport entity;
        if (dto.getId() != null) {
            // 编辑
            entity = this.getById(dto.getId());
            if (entity == null) {
                throw new ServiceException(ResultCode.DATA_NOT_EXIST, "工作汇报");
            }
            BeanUtil.beanCopy(dto, entity);
        } else {
            // 新增
            entity = new WorkReport();
            BeanUtil.beanCopy(dto, entity);
            entity.setUserId(userId);
            entity.setCreateUserId(userId);
            // 设置状态默认值
            if (entity.getStatus() == null) {
                entity.setStatus(WorkReportStatusEnum.DRAFT.getValue());
            }
        }

        // 解析汇报日期，自动计算周数和月数
        if (dto.getReportDate() != null) {
            LocalDate reportDate = LocalDate.parse(dto.getReportDate());
            entity.setReportDate(reportDate);
            entity.setWeekNumber(reportDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
            entity.setMonthNumber(reportDate.getMonthValue());
        }

        this.saveOrUpdate(entity);
    }

    @Override
    public QueryResultVo<WorkReportVo> queryWorkReport(WorkReportQueryDto dto, Long loginUserId) {
        IPage<WorkReportVo> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        Page<WorkReportVo> result = baseMapper.queryWorkReport(dto, page, loginUserId);

        QueryResultVo<WorkReportVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, WorkReportVo.class);
        queryResultVo.setRecords(result.getRecords());

        // 填充枚举描述
        queryResultVo.getRecords().forEach(vo -> {
            vo.setReportTypeDesc(ReportTypeEnum.getDescByValue(vo.getReportType()));
            vo.setStatusDesc(WorkReportStatusEnum.getDescByValue(vo.getStatus()));
        });

        return queryResultVo;
    }

    @Override
    public WorkReportVo getWorkReportDetail(Long id) {
        WorkReportVo vo = baseMapper.getWorkReportDetail(id);
        if (vo == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "工作汇报");
        }
        vo.setReportTypeDesc(ReportTypeEnum.getDescByValue(vo.getReportType()));
        vo.setStatusDesc(WorkReportStatusEnum.getDescByValue(vo.getStatus()));
        // 查询评论
        vo.setComments(reportCommentMapper.queryByReportId(id));
        return vo;
    }

    @Override
    public void deleteWorkReport(Long id) {
        WorkReport entity = this.getById(id);
        if (entity == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "工作汇报");
        }
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitWorkReport(Long id, Long userId) {
        WorkReport entity = this.getById(id);
        if (entity == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "工作汇报");
        }
        if (!entity.getUserId().equals(userId)) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "只能提交自己的汇报");
        }
        entity.setStatus(WorkReportStatusEnum.SUBMITTED.getValue());
        entity.setSubmitTime(LocalDateTime.now());
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComment(ReportCommentAddDto dto, Long userId) {
        WorkReport report = this.getById(dto.getReportId());
        if (report == null) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "工作汇报");
        }
        ReportComment comment = new ReportComment();
        comment.setReportId(dto.getReportId());
        comment.setUserId(userId);
        comment.setContent(dto.getContent());
        reportCommentMapper.insert(comment);
    }

    @Override
    public List<ReportStatVo> queryReportStat(Byte reportType, String startDate, String endDate, Long deptId) {
        return baseMapper.queryReportStat(reportType, startDate, endDate, deptId);
    }
}
