package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ApplicationDto;
import com.xavier.pms.model.Application;
import com.xavier.pms.vo.ApplicationVo;

/**
 * 业务逻辑层-应用接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IApplicationService extends IService<Application> {

    /**
     * 创建应用
     *
     * @param applicationDto 应用入参
     * @return id
     */
    Long createApplication(ApplicationDto applicationDto);

    /**
     * 更新应用
     *
     * @param applicationDto 应用入参
     * @return 是否成功
     */
    Boolean updateApplication(ApplicationDto applicationDto);

    /**
     * 删除应用
     *
     * @param id
     * @return 是否成功
     */
    Boolean deleteApplication(Long id);

    /**
     * 根据ID获取应用信息
     *
     * @param id
     * @return 应用信息
     */
    ApplicationVo getApplication(Long id);

    /**
     * 根据ID获取应用信息
     *
     * @param id
     * @return 应用信息
     */
    Application getBaseApplication(Long id);


}
