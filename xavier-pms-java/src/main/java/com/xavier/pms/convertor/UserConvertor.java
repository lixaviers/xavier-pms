package com.xavier.pms.convertor;

import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.model.User;
import com.xavier.pms.vo.UserVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class UserConvertor {

    private static final BeanCopier beanCopierForUserVo = BeanCopier.create(User.class, UserVo.class, false);
    private static final BeanCopier beanCopierForUser = BeanCopier.create(EmployeeAddDto.class, User.class, false);

    public static UserVo toUserVo(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserVo userVo = new UserVo();
        beanCopierForUserVo.copy(user, userVo, null);
        return userVo;
    }

    public static List<UserVo> toUserVoList(List<User> userList) {
        if (Objects.isNull(userList) || userList.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserVo> userInfoList = new ArrayList<UserVo>(userList.size());
        for (User user : userList) {
            userInfoList.add(toUserVo(user));
        }
        return userInfoList;
    }

}
