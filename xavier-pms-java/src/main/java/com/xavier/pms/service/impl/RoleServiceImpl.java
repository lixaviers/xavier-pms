package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.RoleConvertor;
import com.xavier.pms.dao.RoleMapper;
import com.xavier.pms.dto.RoleDto;
import com.xavier.pms.dto.RoleQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Role;
import com.xavier.pms.model.RoleMenu;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IRoleMenuService;
import com.xavier.pms.service.IRoleService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-角色实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createRole(RoleDto roleDto) {
        Role role = RoleConvertor.toRole(roleDto);
        role.setId(null);
        super.save(role);
        roleMenuService.createRoleMenu(role.getId(), roleDto.getMenuIdList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void updateRole(RoleDto roleDto) {
        getBaseRole(roleDto.getId());
        Role role = RoleConvertor.toRole(roleDto);
        super.updateById(role);
        roleMenuService.updateRoleMenu(role.getId(), roleDto.getMenuIdList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void deleteRole(List<Long> idList) {
        super.removeBatchByIds(idList);
        roleMenuService.remove(RoleMenu.gw().in(RoleMenu::getRoleId, idList));
    }

    @Override
    public RoleVo getRole(Long id) {
        RoleVo roleVo = RoleConvertor.toRoleVo(getBaseRole(id));
        roleVo.setMenuIdList(roleMenuService.getByRoleId(id));
        return roleVo;
    }


    @Override
    public Role getBaseRole(Long id) {
        Role role = super.getById(id);
        if (Objects.isNull(role)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "角色");
        }
        return role;
    }

    @Override
    public QueryResultVo<RoleVo> queryRole(RoleQueryDto queryDTO) {
        Page<Role> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<Role> wrapper = Role.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(Role::getId, queryDTO.getId());
        }
        if (StrUtil.isNotBlank(queryDTO.getRoleName())) {
            // 角色名称不为空
            wrapper.like(Role::getRoleName, queryDTO.getRoleName());
        }
        if (StrUtil.isNotBlank(queryDTO.getRemarks())) {
            // 备注不为空
            wrapper.like(Role::getRemarks, queryDTO.getRemarks());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(Role::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(Role::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(Role::getId);
        Page<Role> result = super.page(page, wrapper);
        QueryResultVo<RoleVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, RoleVo.class);
        queryResultVo.setRecords(RoleConvertor.toRoleVoList(result.getRecords()));
        return queryResultVo;
    }

}
