package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.ApplicationGroupConvertor;
import com.xavier.pms.dao.ApplicationGroupMapper;
import com.xavier.pms.dto.ApplicationGroupDto;
import com.xavier.pms.dto.ApplicationGroupQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.ApplicationGroup;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IApplicationGroupService;
import com.xavier.pms.vo.ApplicationGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-应用分组实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("applicationGroupService")
public class ApplicationGroupServiceImpl extends ServiceImpl<ApplicationGroupMapper, ApplicationGroup> implements IApplicationGroupService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createApplicationGroup(ApplicationGroupDto applicationGroupDto) {
        ApplicationGroup applicationGroup = ApplicationGroupConvertor.toApplicationGroup(applicationGroupDto);
        applicationGroup.setId(null);
        super.save(applicationGroup);
        return applicationGroup.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateApplicationGroup(ApplicationGroupDto applicationGroupDto) {
        getBaseApplicationGroup(applicationGroupDto.getId());
        ApplicationGroup applicationGroup = ApplicationGroupConvertor.toApplicationGroup(applicationGroupDto);
        return super.updateById(applicationGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteApplicationGroup(Long id) {
        getBaseApplicationGroup(id);
        return super.removeById(id);
    }

    @Override
    public ApplicationGroupVo getApplicationGroup(Long id) {
        return ApplicationGroupConvertor.toApplicationGroupVo(getBaseApplicationGroup(id));
    }


    @Override
    public ApplicationGroup getBaseApplicationGroup(Long id) {
        ApplicationGroup applicationGroup = super.getById(id);
        if (Objects.isNull(applicationGroup)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "应用分组");
        }
        return applicationGroup;
    }

    @Override
    public List<ApplicationGroupVo> queryApplicationGroup(ApplicationGroupQueryDto queryDTO) {
        return baseMapper.queryApplicationGroup(queryDTO);
    }

}
