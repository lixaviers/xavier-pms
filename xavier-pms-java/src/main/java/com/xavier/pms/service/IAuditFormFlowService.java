package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.vo.AuditFormFlowVo;
import com.xavier.pms.vo.UserInfoVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务逻辑层-审批单流程接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IAuditFormFlowService extends IService<AuditFormFlow> {

    /**
     * 创建审批单流程
     *
     * @param loginUser 登录用户
     * @param auditForm 审批单流程入参
     */
    void createAuditFlow(UserInfoVo loginUser, AuditForm auditForm);

    /**
     * 修改状态，不做任何判断
     *
     * @param id
     * @param auditStatus
     */
    void updateAuditStatus(Long id, byte auditStatus, LocalDateTime dealTime);

    /**
     * 根据审批单id查询审批进程
     */
    List<AuditFormFlowVo> getProcessByAuditFormId(Long auditFormId);


}
