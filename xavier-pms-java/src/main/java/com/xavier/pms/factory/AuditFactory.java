package com.xavier.pms.factory;

import com.xavier.pms.constants.Constant;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IAuditFormFlowService;

import java.util.Objects;

public class AuditFactory {

    /**
     * @param approvalType 审批类型
     * @return
     */
    public static IAudit getAudit(IAuditFormFlowService auditFormFlowService, IAuditFormFlowDetailService auditFormFlowDetailService, String approvalType) {
        if (Objects.equals(approvalType, Constant.APPROVAL_TYPE_START)) {
            return new AuditStart(auditFormFlowService, auditFormFlowDetailService);
        } else if (Objects.equals(approvalType, Constant.APPROVAL_TYPE_APPROVAL)) {
            return new AuditApproval(auditFormFlowService, auditFormFlowDetailService);
        } else if (Objects.equals(approvalType, Constant.APPROVAL_TYPE_HANDLER)) {
            return new AuditHandler(auditFormFlowService, auditFormFlowDetailService);
        } else if (Objects.equals(approvalType, Constant.APPROVAL_TYPE_CC)) {
            return new AuditCc(auditFormFlowService, auditFormFlowDetailService);
        } else if (Objects.equals(approvalType, Constant.APPROVAL_TYPE_END)) {
            return new AuditEnd(auditFormFlowService, auditFormFlowDetailService);
        }
        return new AuditEnd(auditFormFlowService, auditFormFlowDetailService);
    }

}
