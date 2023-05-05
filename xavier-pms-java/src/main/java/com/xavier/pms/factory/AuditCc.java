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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuditCc implements IAudit {

    @Override
    public byte deal(LocalDateTime now, AuditFormFlow flow, List<AuditFormFlowDetail> auditFormFlowDetailList) {
        auditFormFlowService.updateAuditStatus(flow.getId(), AuditStatusEnum.CC.getValue(), now);
        if (CollUtil.isNotEmpty(auditFormFlowDetailList)) {
            List<AuditFormFlowDetail> updateList = new ArrayList<>();
            List<AuditFormFlowDetail> ccList = auditFormFlowDetailList.stream().filter(bean -> Objects.equals(Constant.APPROVAL_TYPE_CC, bean.getApprovalType())).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(ccList)) {
                // TODO 通知
                for (AuditFormFlowDetail detail : ccList) {
                    updateList.add(new AuditFormFlowDetail(detail.getId(), AuditStatusEnum.CC.getValue(), now));
                }
            }
            auditFormFlowDetailService.updateBatchById(updateList);
        }
        return AuditStatusEnum.PASSED.getValue();
    }


    private final IAuditFormFlowService auditFormFlowService;
    private final IAuditFormFlowDetailService auditFormFlowDetailService;

    public AuditCc(IAuditFormFlowService auditFormFlowService, IAuditFormFlowDetailService auditFormFlowDetailService) {
        this.auditFormFlowService = auditFormFlowService;
        this.auditFormFlowDetailService = auditFormFlowDetailService;
    }

}
