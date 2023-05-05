package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.dto.AuditFormQueryDto;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.AuditFormVo;
import com.xavier.pms.vo.UserInfoVo;

/**
 * 业务逻辑层-审批单接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IAuditFormService extends IService<AuditForm> {

    /**
     * 创建审批单
     *
     * @param loginUser    登录用户
     * @param auditFormDto 审批单入参
     * @return id
     */
    void createAuditForm(UserInfoVo loginUser, AuditFormDto auditFormDto);

    /**
     * 处理审批单流程
     */
    void dealProcess(Long id);

    /**
     * 根据审批单id获取审批单流程信息
     *
     * @param id
     * @return 审批单信息
     */
    AuditFormVo getAuditForm(Long id);

    /**
     * 根据ID获取审批单信息
     *
     * @param id
     * @return 审批单信息
     */
    AuditForm getBaseAuditForm(Long id);

    /**
     * 分页查询审批单信息
     *
     * @param auditFormQueryDto 审批单查询入参
     * @return 审批单信息
     */
    QueryResultVo<AuditFormVo> queryAuditForm(AuditFormQueryDto auditFormQueryDto);


    /**
     * 修改状态，不做任何判断
     *
     * @param id
     * @param auditStatus
     */
    void updateAuditStatus(Long id, byte auditStatus);

}
