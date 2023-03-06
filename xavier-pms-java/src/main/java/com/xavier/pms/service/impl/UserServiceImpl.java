package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.UserMapper;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.User;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.service.IUserTokenService;
import com.xavier.pms.utils.PasswordEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * 业务逻辑层-用户实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PasswordEncoderUtil passwordEncoderUtil;

    @Resource
    private IUserTokenService userTokenService;

    @Override
    public String login(LoginDto dto) {
        // 根据用户名查找
        User user = getByUserName(dto.getUserName());
        if (Objects.isNull(user)) {
            throw new ServiceException(ResultCode.C503);
        }
        // 匹配密码
        if (!passwordEncoderUtil.matches(dto.getUserPwd(), user.getUserPwd())) {
            throw new ServiceException(ResultCode.C503);
        }
        // todo 用户状态

        // 生成token，基于数据库表
        return userTokenService.createUserToken(user.getId());
    }

    @Override
    public User getByToken(String token) {
        return baseMapper.getByToken(token);
    }

    /**
     * 根据用户名查找
     *
     * @param userName 用户名
     * @return
     */
    private User getByUserName(String userName) {
        return super.getOne(User.gw().eq(User::getUserName, userName));
    }


}
