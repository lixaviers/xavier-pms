package com.xavier.pms.convertor;

import com.xavier.pms.model.Role;
import com.xavier.pms.dto.RoleDto;
import com.xavier.pms.vo.RoleVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class RoleConvertor {

    private static final BeanCopier beanCopierForRoleVo = BeanCopier.create(Role.class, RoleVo.class, false);
    private static final BeanCopier beanCopierForRole = BeanCopier.create(RoleDto.class, Role.class, false);

    public static RoleVo toRoleVo(Role role) {
        if (Objects.isNull(role)) {
            return null;
        }
        RoleVo roleVo = new RoleVo();
        beanCopierForRoleVo.copy(role, roleVo, null);
        return roleVo;
    }

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        beanCopierForRole.copy(roleDto, role, null);
        return role;
    }

    public static List<RoleVo> toRoleVoList(List<Role> roleList) {
        if (Objects.isNull(roleList) || roleList.isEmpty()) {
            return new ArrayList<>();
        }
        List<RoleVo> roleInfoList = new ArrayList<RoleVo>(roleList.size());
        for (Role role : roleList) {
            roleInfoList.add(toRoleVo(role));
        }
        return roleInfoList;
    }

}
