package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.vo.ApprovalProcessJsonVo;
import com.xavier.pms.vo.UserInfoVo;

/**
 * 业务逻辑层-审批单流程详细接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IAuditFormFlowDetailService extends IService<AuditFormFlowDetail> {

    /**
     * 创建审批单流程详细
     */
    void createAuditFlowDetail(UserInfoVo loginUser, AuditFormFlow flow, ApprovalProcessJsonVo vo);


}
