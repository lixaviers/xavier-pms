package com.xavier.pms.factory;

import cn.hutool.core.collection.CollUtil;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IAuditFormFlowService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuditHandler implements IAudit {

    @Override
    public byte deal(LocalDateTime now, AuditFormFlow flow, List<AuditFormFlowDetail> auditFormFlowDetailList) {

        if (CollUtil.isEmpty(auditFormFlowDetailList)) {
            // 办理时未找到人
            AuditFormFlow bean = new AuditFormFlow();
            bean.setTitle("自动通过");
            bean.setRemarks("办理人为空，自动通过");
            bean.setAuditStatus(AuditStatusEnum.PASSED.getValue());
            bean.setDealTime(now);
            auditFormFlowService.updateById(bean);
            return AuditStatusEnum.PASSED.getValue();
        } else {
            // 办理人
            List<AuditFormFlowDetail> approvalList = auditFormFlowDetailList.stream().filter(bean -> Constant.APPROVAL_TYPE_HANDLER.equals(bean.getApprovalType())).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(approvalList)) {
                // 通过的数量
                long passeds = approvalList.stream().filter(bean -> AuditStatusEnum.PASSED.equal(bean.getAuditStatus())).count();
                if (passeds == 0) {
                    // 没有人办理
                    List<AuditFormFlowDetail> detailList = new ArrayList<>();
                    if (Objects.equals(flow.getApprovalMoreType(), "order")) {
                        // 按顺序依次办理，只把第一人改为审批中
                        approvalList = approvalList.stream().sorted(Comparator.comparing(AuditFormFlowDetail::getSortNumber)).collect(Collectors.toList());
                        detailList.add(new AuditFormFlowDetail(approvalList.get(0).getId(), AuditStatusEnum.UNDER.getValue(), null));
                    } else {
                        for (AuditFormFlowDetail detail : approvalList) {
                            detailList.add(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.UNDER.getValue(), null));
                        }
                    }
                    auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.UNDER.getValue(), null);
                    // TODO 通知办理人
                    auditFormFlowDetailService.updateBatchById(detailList);
                    return AuditStatusEnum.UNDER.getValue();
                } else {
                    if (!Objects.equals(flow.getApprovalMoreType(), "one") && passeds != approvalList.size()) {
                        // 审批中
                        if (Objects.equals(flow.getApprovalMoreType(), "order")) {
                            // 处理按顺序依次办理
                            approvalList = approvalList.stream().sorted(Comparator.comparing(AuditFormFlowDetail::getSortNumber)).collect(Collectors.toList());
                            for (AuditFormFlowDetail detail : approvalList) {
                                if (AuditStatusEnum.PENDING.equal(detail.getAuditStatus())) {
                                    // 将第一个是待处理的改为审批中
                                    // TODO 通知办理人
                                    auditFormFlowDetailService.updateById(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.UNDER.getValue(), null));
                                }
                            }
                        }
                        return AuditStatusEnum.UNDER.getValue();
                    }
                }
            }

            List<AuditFormFlowDetail> ccList = auditFormFlowDetailList.stream().filter(bean -> Constant.APPROVAL_TYPE_CC.equals(bean.getApprovalType())).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(ccList)) {
                List<AuditFormFlowDetail> detailList = new ArrayList<>();
                for (AuditFormFlowDetail detail : ccList) {
                    detailList.add(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.PASSED.getValue(), now));
                }
                // TODO 通知抄送人
                auditFormFlowDetailService.updateBatchById(detailList);
            }
            auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.PASSED.getValue(), now);
            return AuditStatusEnum.PASSED.getValue();
        }


    }


    private final IAuditFormFlowService auditFormFlowService;
    private final IAuditFormFlowDetailService auditFormFlowDetailService;

    public AuditHandler(IAuditFormFlowService auditFormFlowService, IAuditFormFlowDetailService auditFormFlowDetailService) {
        this.auditFormFlowService = auditFormFlowService;
        this.auditFormFlowDetailService = auditFormFlowDetailService;
    }

}
