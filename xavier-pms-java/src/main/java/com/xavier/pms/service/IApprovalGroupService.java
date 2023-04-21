package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ApprovalGroupDto;
import com.xavier.pms.dto.ApprovalGroupQueryDto;
import com.xavier.pms.model.ApprovalGroup;
import com.xavier.pms.vo.ApprovalGroupVo;

import java.util.List;

/**
 * 业务逻辑层-审批分组接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IApprovalGroupService extends IService<ApprovalGroup> {

    /**
     * 创建审批分组
     *
     * @param approvalGroupDto 审批分组入参
     * @return id
     */
    Long createApprovalGroup(ApprovalGroupDto approvalGroupDto);

    /**
     * 更新审批分组
     *
     * @param approvalGroupDto 审批分组入参
     * @return 是否成功
     */
    Boolean updateApprovalGroup(ApprovalGroupDto approvalGroupDto);

    /**
     * 删除审批分组
     *
     * @param id
     * @return 是否成功
     */
    Boolean deleteApprovalGroup(Long id);

    /**
     * 根据ID获取审批分组信息
     *
     * @param id
     * @return 审批分组信息
     */
    ApprovalGroupVo getApprovalGroup(Long id);

    /**
     * 根据ID获取审批分组信息
     *
     * @param id
     * @return 审批分组信息
     */
    ApprovalGroup getBaseApprovalGroup(Long id);

    /**
     * 分页查询审批分组信息
     *
     * @param approvalGroupQueryDto 审批分组查询入参
     * @return 审批分组信息
     */
    List<ApprovalGroupVo> queryApprovalGroup(ApprovalGroupQueryDto approvalGroupQueryDto);


}
