package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.convertor.AuditFormConvertor;
import com.xavier.pms.dao.AuditFormFlowMapper;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IAuditFormFlowService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ApprovalProcessJsonVo;
import com.xavier.pms.vo.AuditFormFlowDetailVo;
import com.xavier.pms.vo.AuditFormFlowVo;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 业务逻辑层-审批单流程实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("auditFormFlowService")
public class AuditFormFlowServiceImpl extends ServiceImpl<AuditFormFlowMapper, AuditFormFlow> implements IAuditFormFlowService {

    @Resource
    private IAuditFormFlowDetailService auditFlowDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuditFlow(UserInfoVo loginUser, AuditForm auditForm) {
        List<ApprovalProcessJsonVo> vos = JSON.parseArray(auditForm.getApprovalProcess(), ApprovalProcessJsonVo.class);
        for (int i = 0; i < vos.size(); i++) {
            ApprovalProcessJsonVo vo = vos.get(i);

            AuditFormFlow flow = BeanUtil.beanCopy(vo, AuditFormFlow.class);
            flow.setId(null);
            flow.setAuditFormId(auditForm.getId());
            flow.setAuditStatus(AuditStatusEnum.PENDING.getValue());
            flow.setProcessNode(i + 1);
            super.save(flow);

            auditFlowDetailService.createAuditFlowDetail(loginUser, flow, vo);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAuditStatus(Long id, byte auditStatus, LocalDateTime dealTime) {
        AuditFormFlow bean = new AuditFormFlow();
        bean.setId(id);
        bean.setAuditStatus(auditStatus);
        bean.setDealTime(dealTime);
        super.updateById(bean);
    }

    @Override
    public List<AuditFormFlowVo> getProcessByAuditFormId(Long auditFormId) {
//        AuditForm auditForm = auditFormService.getBaseAuditForm(auditFormId);
        List<AuditFormFlowVo> voList = new ArrayList<>();
        List<AuditFormFlow> flowList = super.list(AuditFormFlow.gw()
                .eq(AuditFormFlow::getAuditFormId, auditFormId));
        if (CollUtil.isNotEmpty(flowList)) {
            List<Long> ids = flowList.stream().map(AuditFormFlow::getId).collect(Collectors.toList());
            Map<Long, List<AuditFormFlowDetail>> map = null;
            List<AuditFormFlowDetail> detailList = auditFlowDetailService.list(AuditFormFlowDetail.gw().in(AuditFormFlowDetail::getAuditFormFlowId, ids));
            if (CollUtil.isNotEmpty(detailList)) {
                map = detailList.stream().collect(Collectors.groupingBy(AuditFormFlowDetail::getAuditFormFlowId));
            }
            for (AuditFormFlow auditFormFlow : flowList) {
                if (CollUtil.isNotEmpty(map)) {
                    List<AuditFormFlowDetail> details = map.get(auditFormFlow.getId());
                    if (CollUtil.isNotEmpty(details)) {
                        List<AuditFormFlowDetail> notccList = details.stream().filter(bean -> !Constant.APPROVAL_TYPE_CC.equals(bean.getApprovalType())).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(notccList)) {
                            if ((Objects.equals(auditFormFlow.getApprovalType(), Constant.APPROVAL_TYPE_APPROVAL) || Objects.equals(auditFormFlow.getApprovalType(), Constant.APPROVAL_TYPE_HANDLER)) &&
                                    !Objects.equals(auditFormFlow.getApprovalMoreType(), "one")) {
                                // 多人时采用方式 不是 一名审批人同意即可
                                for (AuditFormFlowDetail detail : notccList) {
                                    voList.add(AuditFormConvertor.toAuditFormFlowVo(auditFormFlow, CollUtil.toList(detail)));
                                }
                            } else {
                                voList.add(AuditFormConvertor.toAuditFormFlowVo(auditFormFlow, notccList));
                            }
                        }
                        List<AuditFormFlowDetail> ccList = details.stream().filter(bean -> Constant.APPROVAL_TYPE_CC.equals(bean.getApprovalType())).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(ccList)) {
                            AuditFormFlowVo vo = AuditFormConvertor.toAuditFormFlowVo(auditFormFlow, ccList);
                            if (!Objects.equals(auditFormFlow.getApprovalType(), Constant.APPROVAL_TYPE_END)) {
                                vo.setTitle("抄送");
                            }
                            vo.setAuditStatus(AuditStatusEnum.CC.getValue());
                            voList.add(vo);
                        }
                    }
                }
            }
        }
        return voList;
    }

}
