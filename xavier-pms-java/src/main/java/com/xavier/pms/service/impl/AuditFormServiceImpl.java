package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.AuditFormConvertor;
import com.xavier.pms.dao.AuditFormMapper;
import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.dto.AuditFormQueryDto;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.factory.AuditFactory;
import com.xavier.pms.factory.IAudit;
import com.xavier.pms.model.Approval;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IApprovalService;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IAuditFormFlowService;
import com.xavier.pms.service.IAuditFormService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.AuditFormVo;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-审批单实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("auditFormService")
public class AuditFormServiceImpl extends ServiceImpl<AuditFormMapper, AuditForm> implements IAuditFormService {

    @Resource
    private IApprovalService approvalService;
    @Resource
    private IAuditFormFlowService auditFormFlowService;
    @Resource
    private IAuditFormFlowDetailService auditFormFlowDetailService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createAuditForm(UserInfoVo loginUser, AuditFormDto auditFormDto) {
        Approval approval = approvalService.getBaseApproval(auditFormDto.getApprovalId());
        AuditForm auditForm = AuditFormConvertor.toAuditForm(auditFormDto);
        auditForm.setId(null);
        auditForm.setUserId(loginUser.getId());
        auditForm.setNickName(loginUser.getNickName());
        auditForm.setApprovalName(approval.getApprovalName());
        auditForm.setAuditStatus(AuditStatusEnum.UNDER.getValue());
        auditForm.setApprovalProcess(approval.getProcess());
        auditForm.setForm(approval.getForm());
        super.save(auditForm);

        // 生成审批流程数据
        auditFormFlowService.createAuditFlow(loginUser, auditForm);
        dealProcess(auditForm.getId());
    }


    /**
     * 处理审批单流程
     *
     * @param auditFormId 审批单id
     */
    @Override
    public void dealProcess(Long auditFormId) {
        AuditForm auditForm = getBaseAuditForm(auditFormId);
        if (AuditStatusEnum.PASSED.equal(auditForm.getAuditStatus()) || AuditStatusEnum.REJECTED.equal(auditForm.getAuditStatus()) || AuditStatusEnum.WITHDRAWN.equal(auditForm.getAuditStatus())) {
            // 流程结束的不做处理
            return;
        }

        List<AuditFormFlow> flowList = auditFormFlowService.list(AuditFormFlow.gw().eq(AuditFormFlow::getAuditFormId, auditFormId));
        if (CollUtil.isEmpty(flowList)) {
            if (AuditStatusEnum.PENDING.equal(auditForm.getAuditStatus())) {
                // 待处理改成已通过
                updateAuditStatus(auditForm.getId(), AuditStatusEnum.PASSED.getValue());
            }
            return;
        }

        if (AuditStatusEnum.PENDING.equal(auditForm.getAuditStatus())) {
            // 待处理改成审批中
            updateAuditStatus(auditForm.getId(), AuditStatusEnum.UNDER.getValue());
        }

        Byte lastAuditStatus = AuditStatusEnum.PASSED.getValue();
        for (AuditFormFlow flow : flowList) {
            if (AuditStatusEnum.PASSED.equal(flow.getAuditStatus())) {
                // 已通过
                lastAuditStatus = AuditStatusEnum.PASSED.getValue();
                continue;
            } else if (AuditStatusEnum.REJECTED.equal(flow.getAuditStatus())) {
                // 已拒绝
                lastAuditStatus = AuditStatusEnum.REJECTED.getValue();
                break;
            } else if (AuditStatusEnum.UNDER.equal(lastAuditStatus)) {
                // 上一个在审批中
                return;
            }
            List<AuditFormFlowDetail> detailList = auditFormFlowDetailService.list(AuditFormFlowDetail.gw().eq(AuditFormFlowDetail::getAuditFormFlowId, flow.getId()));
            IAudit iAudit = AuditFactory.getAudit(auditFormFlowService, auditFormFlowDetailService, flow.getApprovalType());
            lastAuditStatus = iAudit.deal(LocalDateTime.now(), flow, detailList);
        }
        if (AuditStatusEnum.PASSED.equal(lastAuditStatus) || AuditStatusEnum.REJECTED.equal(lastAuditStatus)) {
            // 最后流程为已通过/已拒绝
            updateAuditStatus(auditForm.getId(), lastAuditStatus);
        }
    }

    @Override
    public AuditFormVo getAuditForm(Long id) {
        return AuditFormConvertor.toAuditFormVo(getBaseAuditForm(id));
    }


    @Override
    public AuditForm getBaseAuditForm(Long id) {
        AuditForm auditForm = super.getById(id);
        if (Objects.isNull(auditForm)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "审批单");
        }
        return auditForm;
    }

    @Override
    public QueryResultVo<AuditFormVo> queryAuditForm(AuditFormQueryDto queryDTO) {
        Page<AuditForm> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<AuditForm> wrapper = AuditForm.gw().select(
                AuditForm::getId, AuditForm::getUserId, AuditForm::getNickName, AuditForm::getApprovalId, AuditForm::getApprovalName, AuditForm::getAuditStatus, AuditForm::getCreateTime
        ).eq(AuditForm::getUserId, queryDTO.getUserId());

        if (Objects.nonNull(queryDTO.getAuditStatus())) {
            // 审批状态不为空
            wrapper.eq(AuditForm::getAuditStatus, queryDTO.getAuditStatus());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(AuditForm::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(AuditForm::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(AuditForm::getId);
        Page<AuditForm> result = super.page(page, wrapper);
        QueryResultVo<AuditFormVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, AuditFormVo.class);
        queryResultVo.setRecords(AuditFormConvertor.toAuditFormVoList(result.getRecords()));
        return queryResultVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void updateAuditStatus(Long id, byte auditStatus) {
        AuditForm bean = new AuditForm();
        bean.setId(id);
        bean.setAuditStatus(auditStatus);
        super.updateById(bean);
    }
}
