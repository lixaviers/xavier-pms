package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.model.RoleMenu;

import java.util.List;

/**
 * 业务逻辑层-角色菜单接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 创建
     */
    void createRoleMenu(Long roleId, List<Long> menuIdList);

    /**
     * 更新
     *
     * @return 是否成功
     */
    void updateRoleMenu(Long roleId, List<Long> menuIdList);


    /**
     * 根据角色id查询菜单id列表
     *
     * @param roleId
     * @return
     */
    List<Long> getByRoleId(Long roleId);

}
