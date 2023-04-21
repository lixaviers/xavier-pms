package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.ApprovalGroupConvertor;
import com.xavier.pms.dao.ApprovalGroupMapper;
import com.xavier.pms.dto.ApprovalGroupDto;
import com.xavier.pms.dto.ApprovalGroupQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.ApprovalGroup;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IApprovalGroupService;
import com.xavier.pms.vo.ApprovalGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-审批分组实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("approvalGroupService")
public class ApprovalGroupServiceImpl extends ServiceImpl<ApprovalGroupMapper, ApprovalGroup> implements IApprovalGroupService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createApprovalGroup(ApprovalGroupDto approvalGroupDto) {
        ApprovalGroup approvalGroup = ApprovalGroupConvertor.toApprovalGroup(approvalGroupDto);
        approvalGroup.setId(null);
        super.save(approvalGroup);
        return approvalGroup.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateApprovalGroup(ApprovalGroupDto approvalGroupDto) {
        getBaseApprovalGroup(approvalGroupDto.getId());
        ApprovalGroup approvalGroup = ApprovalGroupConvertor.toApprovalGroup(approvalGroupDto);
        return super.updateById(approvalGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteApprovalGroup(Long id) {
        getBaseApprovalGroup(id);
        return super.removeById(id);
    }

    @Override
    public ApprovalGroupVo getApprovalGroup(Long id) {
        return ApprovalGroupConvertor.toApprovalGroupVo(getBaseApprovalGroup(id));
    }


    @Override
    public ApprovalGroup getBaseApprovalGroup(Long id) {
        ApprovalGroup approvalGroup = super.getById(id);
        if (Objects.isNull(approvalGroup)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "审批分组");
        }
        return approvalGroup;
    }

    @Override
    public List<ApprovalGroupVo> queryApprovalGroup(ApprovalGroupQueryDto queryDTO) {
        return baseMapper.queryApprovalGroup(queryDTO);
    }

}
