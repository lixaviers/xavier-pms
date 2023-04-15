package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ApplicationGroupDto;
import com.xavier.pms.dto.ApplicationGroupQueryDto;
import com.xavier.pms.model.ApplicationGroup;
import com.xavier.pms.vo.ApplicationGroupVo;

import java.util.List;

/**
 * 业务逻辑层-应用分组接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IApplicationGroupService extends IService<ApplicationGroup> {

    /**
     * 创建应用分组
     *
     * @param applicationGroupDto 应用分组入参
     * @return id
     */
    Long createApplicationGroup(ApplicationGroupDto applicationGroupDto);

    /**
     * 更新应用分组
     *
     * @param applicationGroupDto 应用分组入参
     * @return 是否成功
     */
    Boolean updateApplicationGroup(ApplicationGroupDto applicationGroupDto);

    /**
     * 删除应用分组
     *
     * @param id
     * @return 是否成功
     */
    Boolean deleteApplicationGroup(Long id);

    /**
     * 根据ID获取应用分组信息
     *
     * @param id
     * @return 应用分组信息
     */
    ApplicationGroupVo getApplicationGroup(Long id);

    /**
     * 根据ID获取应用分组信息
     *
     * @param id
     * @return 应用分组信息
     */
    ApplicationGroup getBaseApplicationGroup(Long id);

    /**
     * 分页查询应用分组信息
     *
     * @param applicationGroupQueryDto 应用分组查询入参
     * @return 应用分组信息
     */
    List<ApplicationGroupVo> queryApplicationGroup(ApplicationGroupQueryDto applicationGroupQueryDto);


}
