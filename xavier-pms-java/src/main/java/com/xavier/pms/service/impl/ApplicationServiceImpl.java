package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.ApplicationConvertor;
import com.xavier.pms.dao.ApplicationMapper;
import com.xavier.pms.dto.ApplicationDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Application;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IApplicationService;
import com.xavier.pms.vo.ApplicationDetailVo;
import com.xavier.pms.vo.ApplicationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * 业务逻辑层-应用实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("applicationService")
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createApplication(ApplicationDto applicationDto) {
        Application application = ApplicationConvertor.toApplication(applicationDto);
        application.setId(null);
        super.save(application);
        return application.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateApplication(ApplicationDto applicationDto) {
        getBaseApplication(applicationDto.getId());
        Application application = ApplicationConvertor.toApplication(applicationDto);
        return super.updateById(application);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteApplication(Long id) {
        getBaseApplication(id);
        return super.removeById(id);
    }

    @Override
    public ApplicationDetailVo getApplication(Long id) {
        return ApplicationConvertor.toApplicationDetailVo(getBaseApplication(id));
    }


    @Override
    public Application getBaseApplication(Long id) {
        Application application = super.getById(id);
        if (Objects.isNull(application)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "应用");
        }
        return application;
    }


}
