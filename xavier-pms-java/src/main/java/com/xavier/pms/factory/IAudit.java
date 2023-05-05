package com.xavier.pms.factory;

import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface IAudit {

    byte deal(LocalDateTime now, AuditFormFlow flow, List<AuditFormFlowDetail> detailList);

}
