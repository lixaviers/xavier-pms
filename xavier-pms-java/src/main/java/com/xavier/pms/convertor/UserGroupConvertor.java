package com.xavier.pms.convertor;

import com.alibaba.fastjson2.JSON;
import com.xavier.pms.dto.UserGroupDto;
import com.xavier.pms.model.UserGroup;
import com.xavier.pms.vo.EmployeeJsonVo;
import com.xavier.pms.vo.UserGroupVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 员工组对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class UserGroupConvertor {

    private static final BeanCopier beanCopierForUserGroupVo = BeanCopier.create(UserGroup.class, UserGroupVo.class, false);
    private static final BeanCopier beanCopierForUserGroup = BeanCopier.create(UserGroupDto.class, UserGroup.class, false);

    public static UserGroupVo toUserGroupVo(UserGroup userGroup) {
        if (Objects.isNull(userGroup)) {
            return null;
        }
        UserGroupVo userGroupVo = new UserGroupVo();
        beanCopierForUserGroupVo.copy(userGroup, userGroupVo, null);
        userGroupVo.setEmployeeList(JSON.parseArray(userGroup.getMemberJson(), EmployeeJsonVo.class));
        return userGroupVo;
    }

    public static UserGroup toUserGroup(UserGroupDto userGroupDto) {
        UserGroup userGroup = new UserGroup();
        beanCopierForUserGroup.copy(userGroupDto, userGroup, null);
        userGroup.setMemberJson(JSON.toJSONString(userGroupDto.getEmployeeList()));
        return userGroup;
    }

    public static List<UserGroupVo> toUserGroupVoList(List<UserGroup> userGroupList) {
        if (Objects.isNull(userGroupList) || userGroupList.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserGroupVo> userGroupInfoList = new ArrayList<UserGroupVo>(userGroupList.size());
        for (UserGroup userGroup : userGroupList) {
            userGroupInfoList.add(toUserGroupVo(userGroup));
        }
        return userGroupInfoList;
    }

}
