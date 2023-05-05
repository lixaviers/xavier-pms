package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.RoleDto;
import com.xavier.pms.dto.RoleQueryDto;
import com.xavier.pms.vo.RoleVo;
import com.xavier.pms.model.Role;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-角色接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IRoleService extends IService<Role> {

    /**
     * 创建角色
     *
     * @param roleDto 角色入参
     */
    void createRole(RoleDto roleDto);

    /**
     * 更新角色
     *
     * @param roleDto 角色入参
     * @return 是否成功
     */
    void updateRole(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param idList
     * @return 是否成功
     */
    void deleteRole(List<Long> idList);

    /**
     * 根据ID获取角色信息
     *
     * @param id
     * @return 角色信息
     */
    RoleVo getRole(Long id);

    /**
     * 根据ID获取角色信息
     *
     * @param id
     * @return 角色信息
     */
    Role getBaseRole(Long id);

    /**
     * 分页查询角色信息
     *
     * @param roleQueryDto 角色查询入参
     * @return 角色信息
     */
    QueryResultVo<RoleVo> queryRole(RoleQueryDto roleQueryDto);



}
