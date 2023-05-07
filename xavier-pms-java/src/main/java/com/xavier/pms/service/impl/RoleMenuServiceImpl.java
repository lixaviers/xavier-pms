package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.RoleMenuMapper;
import com.xavier.pms.model.RoleMenu;
import com.xavier.pms.service.IRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 业务逻辑层-角色菜单实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRoleMenu(Long roleId, List<Long> menuIdList) {
        if (CollUtil.isEmpty(menuIdList)) {
            return;
        }
        List<RoleMenu> list = new ArrayList<>();
        for (Long menuId : menuIdList) {
            list.add(new RoleMenu(roleId, menuId));
        }
        super.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(Long roleId, List<Long> menuIdList) {
        super.remove(RoleMenu.gw().eq(RoleMenu::getRoleId, roleId));
        createRoleMenu(roleId, menuIdList);
    }

    @Override
    public List<Long> getByRoleId(Long roleId) {
        List<RoleMenu> list = super.list(RoleMenu.gw().eq(RoleMenu::getRoleId, roleId));
        if (CollUtil.isNotEmpty(list)) {
            return list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        }
        return null;
    }
}
