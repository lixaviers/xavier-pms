package com.xavier.pms.factory;

import cn.hutool.core.collection.CollUtil;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IAuditFormFlowService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuditApproval implements IAudit {

    @Override
    public byte deal(LocalDateTime now, AuditFormFlow flow, List<AuditFormFlowDetail> auditFormFlowDetailList) {
        if (Objects.equals(flow.getApprovalMode(), "autoPass")) {
            // 自动通过
            pass(now);
            return AuditStatusEnum.PASSED.getValue();
        } else {
            // 人工审批
            if (CollUtil.isEmpty(auditFormFlowDetailList)) {
                // 审批时未找到人
                pass(now);
                return AuditStatusEnum.PASSED.getValue();
            } else {
                // 审批人
                List<AuditFormFlowDetail> approvalList = auditFormFlowDetailList.stream().filter(bean -> Constant.APPROVAL_TYPE_APPROVAL.equals(bean.getApprovalType())).collect(Collectors.toList());
                if (CollUtil.isNotEmpty(approvalList)) {
                    long rejecteds = approvalList.stream().filter(bean -> AuditStatusEnum.REJECTED.equal(bean.getAuditStatus())).count();
                    if (rejecteds > 0) {
                        // 有拒绝的人
                        auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.REJECTED.getValue(), now);
                        return AuditStatusEnum.REJECTED.getValue();
                    }
                    // 通过的数量
                    long passeds = approvalList.stream().filter(bean -> AuditStatusEnum.PASSED.equal(bean.getAuditStatus())).count();
                    if (passeds == 0) {
                        // 没有人审批
                        List<AuditFormFlowDetail> detailList = new ArrayList<>();
                        if (Objects.equals(flow.getApprovalMoreType(), "order")) {
                            // 按顺序依次审批，只把第一人改为审批中
                            approvalList = approvalList.stream().sorted(Comparator.comparing(AuditFormFlowDetail::getSortNumber)).collect(Collectors.toList());
                            detailList.add(new AuditFormFlowDetail(approvalList.get(0).getId(), AuditStatusEnum.UNDER.getValue(), now));
                        } else {
                            for (AuditFormFlowDetail detail : approvalList) {
                                detailList.add(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.UNDER.getValue(), now));
                            }
                        }
                        auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.UNDER.getValue(), now);
                        // TODO 通知审批人
                        auditFormFlowDetailService.updateBatchById(detailList);
                        return AuditStatusEnum.UNDER.getValue();
                    } else {
                        if (!Objects.equals(flow.getApprovalMoreType(), "one") && passeds != auditFormFlowDetailList.size()) {
                            // 审批中
                            if (Objects.equals(flow.getApprovalMoreType(), "order")) {
                                // 处理按顺序依次审批
                                approvalList = approvalList.stream().sorted(Comparator.comparing(AuditFormFlowDetail::getSortNumber)).collect(Collectors.toList());
                                for (AuditFormFlowDetail detail : approvalList) {
                                    if (AuditStatusEnum.PENDING.equal(detail.getAuditStatus())) {
                                        // 将第一个是待处理的改为审批中
                                        // TODO 通知审批人
                                        auditFormFlowDetailService.updateById(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.UNDER.getValue(), now));
                                    }
                                }
                            }
                            return AuditStatusEnum.UNDER.getValue();
                        }
                    }
                }

                List<AuditFormFlowDetail> ccList = auditFormFlowDetailList.stream().filter(bean -> Constant.APPROVAL_TYPE_CC.equals(bean.getApprovalType())).collect(Collectors.toList());
                if (CollUtil.isNotEmpty(ccList)) {
                    List<AuditFormFlowDetail> updateList = new ArrayList<>();
                    for (AuditFormFlowDetail detail : ccList) {
                        updateList.add(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.PASSED.getValue(), now));
                    }
                    // TODO 通知抄送人
                    auditFormFlowDetailService.updateBatchById(updateList);
                }
                auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.PASSED.getValue(), now);
                return AuditStatusEnum.PASSED.getValue();
            }
        }

    }

    private void pass(LocalDateTime now) {
        AuditFormFlow bean = new AuditFormFlow();
        bean.setTitle("自动通过");
        bean.setRemarks("审批人为空，自动通过");
        bean.setAuditStatus(AuditStatusEnum.PASSED.getValue());
        bean.setDealTime(now);
        auditFormFlowService.updateById(bean);
    }

    private final IAuditFormFlowService auditFormFlowService;
    private final IAuditFormFlowDetailService auditFormFlowDetailService;

    public AuditApproval(IAuditFormFlowService auditFormFlowService, IAuditFormFlowDetailService auditFormFlowDetailService) {
        this.auditFormFlowService = auditFormFlowService;
        this.auditFormFlowDetailService = auditFormFlowDetailService;
    }

}
