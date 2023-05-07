package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.convertor.AuditFormConvertor;
import com.xavier.pms.dao.AuditFormMapper;
import com.xavier.pms.dto.AuditFormAuditDto;
import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.dto.AuditFormQueryDto;
import com.xavier.pms.dto.AuditFormRevocationDto;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    @Transactional(rollbackFor = Exception.class)
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

        for (int i = 0; i < flowList.size(); i++) {
            AuditFormFlow flow = flowList.get(i);
            if (AuditStatusEnum.PASSED.equal(flow.getAuditStatus())) {
                // 已通过
                lastAuditStatus = AuditStatusEnum.PASSED.getValue();
                continue;
            } else if (AuditStatusEnum.REJECTED.equal(flow.getAuditStatus()) || AuditStatusEnum.REJECTED.equal(lastAuditStatus)) {
                // 已拒绝
                lastAuditStatus = AuditStatusEnum.REJECTED.getValue();

                // 删除后续的流程
                auditFormFlowDetailService.remove(AuditFormFlowDetail.gw()
                        .eq(AuditFormFlowDetail::getAuditFormId, auditFormId)
                        .in(AuditFormFlowDetail::getAuditStatus, CollUtil.toList(AuditStatusEnum.UNDER.getValue(), AuditStatusEnum.PENDING.getValue())));
                if (i != flowList.size() - 1) {
                    List<Long> idList = new ArrayList<>();
                    for (int j = i; j < flowList.size(); j++) {
                        idList.add(flowList.get(j).getId());
                    }
                    auditFormFlowService.removeByIds(idList);
                }

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
    public AuditFormVo getAuditForm(Long id, Long userId) {
        AuditForm auditForm = getBaseAuditForm(id);
        AuditFormVo auditFormVo = AuditFormConvertor.toAuditFormVo(auditForm);
        AuditFormVo.Button button = new AuditFormVo.Button();
        if (AuditStatusEnum.UNDER.equal(auditForm.getAuditStatus())) {
            // 审批中
            if (Objects.equals(auditForm.getUserId(), userId)) {
                button.setRevocation(true);
            }
            List<AuditFormFlowDetail> list = auditFormFlowDetailService.list(AuditFormFlowDetail.gw()
                    .eq(AuditFormFlowDetail::getAuditFormId, id)
                    .eq(AuditFormFlowDetail::getUserId, userId)
                    .eq(AuditFormFlowDetail::getAuditStatus, AuditStatusEnum.UNDER.getValue()));
            if (CollUtil.isNotEmpty(list)) {
                if (Objects.equals(list.get(0).getApprovalType(), Constant.APPROVAL_TYPE_HANDLER)) {
                    button.setHandler(true);
                } else {
                    button.setAudit(true);
                }
            }
        }
        auditFormVo.setButton(button);
        return auditFormVo;
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

        Page<AuditForm> result = null;
        if (Objects.equals(queryDTO.getType(), "self")) {
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
            result = super.page(page, wrapper);
        } else if (Objects.equals(queryDTO.getType(), "deal")) {
            if (Objects.nonNull(queryDTO.getAuditStatus()) && queryDTO.getAuditStatus().intValue() == 1) {
                queryDTO.setAuditStatusList(CollUtil.toList(AuditStatusEnum.PASSED.getValue(), AuditStatusEnum.REJECTED.getValue()));
            } else {
                queryDTO.setAuditStatusList(CollUtil.toList(AuditStatusEnum.UNDER.getValue()));
            }
            queryDTO.setAuditStatus(null);
            queryDTO.setApprovalTypeList(CollUtil.toList(Constant.APPROVAL_TYPE_APPROVAL, Constant.APPROVAL_TYPE_HANDLER));
            result = baseMapper.queryAuditForm(queryDTO, page);
        } else if (Objects.equals(queryDTO.getType(), "cc")) {
            queryDTO.setAuditStatus(AuditStatusEnum.CC.getValue());
            queryDTO.setApprovalTypeList(CollUtil.toList(Constant.APPROVAL_TYPE_CC));
            result = baseMapper.queryAuditForm(queryDTO, page);
        }
        QueryResultVo<AuditFormVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, AuditFormVo.class);
        queryResultVo.setRecords(AuditFormConvertor.toAuditFormVoList(result.getRecords()));
        return queryResultVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAuditStatus(Long id, byte auditStatus) {
        AuditForm bean = new AuditForm();
        bean.setId(id);
        bean.setAuditStatus(auditStatus);
        super.updateById(bean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void revocation(AuditFormRevocationDto dto, UserInfoVo userInfoVo) {
        // 1、审批单状态判断
        AuditForm auditForm = getBaseAuditForm(dto.getId());
        if (!Objects.equals(auditForm.getUserId(), userInfoVo.getId())) {
            throw new ServiceException("审批单不能撤回");
        }
        if (!AuditStatusEnum.UNDER.equal(auditForm.getAuditStatus())) {
            throw new ServiceException("审批单不能撤回");
        }

        LocalDateTime now = LocalDateTime.now();

        // 2、审批单改为已撤回状态
        AuditForm bean = new AuditForm();
        bean.setId(dto.getId());
        bean.setAuditStatus(AuditStatusEnum.WITHDRAWN.getValue());
        super.updateById(bean);
        // 3、删除正在处理的节点及之后的节点
        List<Byte> statusList = CollUtil.toList(AuditStatusEnum.UNDER.getValue(), AuditStatusEnum.PENDING.getValue());
        List<AuditFormFlow> list = auditFormFlowService.list(AuditFormFlow.gw()
                .eq(AuditFormFlow::getAuditFormId, dto.getId())
                .in(AuditFormFlow::getAuditStatus, statusList));
        if (CollUtil.isNotEmpty(list)) {
            List<Long> idList = list.stream().map(AuditFormFlow::getId).collect(Collectors.toList());
            auditFormFlowDetailService.remove(AuditFormFlowDetail.gw()
                    .in(AuditFormFlowDetail::getAuditFormFlowId, idList)
                    .in(AuditFormFlowDetail::getAuditStatus, statusList));
            auditFormFlowService.removeBatchByIds(idList);
        }
        // 4、增加提交节点，为已撤回
        AuditFormFlow flow = new AuditFormFlow();
        flow.setAuditFormId(dto.getId());
        flow.setTitle("提交");
        flow.setApprovalType(Constant.APPROVAL_TYPE_END);
        flow.setProcessNode(1);
        flow.setAuditStatus(AuditStatusEnum.WITHDRAWN.getValue());
        flow.setRemarks(dto.getRemarks());
        flow.setDealTime(now);
        auditFormFlowService.save(flow);

        AuditFormFlowDetail detail = new AuditFormFlowDetail();
        detail.setAuditFormId(dto.getId());
        detail.setAuditFormFlowId(flow.getId());
        detail.setUserId(userInfoVo.getId());
        detail.setNickName(userInfoVo.getNickName());
        detail.setApprovalType(Constant.APPROVAL_TYPE_END);
        detail.setAuditStatus(AuditStatusEnum.WITHDRAWN.getValue());
        detail.setSortNumber(1);
        detail.setRemarks(dto.getRemarks());
        detail.setDealTime(now);
        auditFormFlowDetailService.save(detail);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(AuditFormAuditDto dto, UserInfoVo loginUser) {
        // 1、审批单状态判断
        AuditForm auditForm = getBaseAuditForm(dto.getId());
        if (!AuditStatusEnum.UNDER.equal(auditForm.getAuditStatus())) {
            throw new ServiceException("审批单不能操作");
        }
        AuditFormFlowDetail auditFormFlowDetail = auditFormFlowDetailService.getOne(AuditFormFlowDetail.gw()
                .in(AuditFormFlowDetail::getAuditFormId, dto.getId())
                .eq(AuditFormFlowDetail::getUserId, loginUser.getId())
                .eq(AuditFormFlowDetail::getAuditStatus, AuditStatusEnum.UNDER.getValue()));
        if (Objects.isNull(auditFormFlowDetail)) {
            throw new ServiceException("审批单不能操作");
        }

        // 2、通过/拒绝
        AuditFormFlowDetail bean = new AuditFormFlowDetail();
        bean.setId(auditFormFlowDetail.getId());
        bean.setAuditStatus(dto.getPass() ? AuditStatusEnum.PASSED.getValue() : AuditStatusEnum.REJECTED.getValue());
        bean.setDealTime(LocalDateTime.now());
        bean.setRemarks(dto.getRemarks());
        auditFormFlowDetailService.updateById(bean);
        dealProcess(dto.getId());
    }

}
