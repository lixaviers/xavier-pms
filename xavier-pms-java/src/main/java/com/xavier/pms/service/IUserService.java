package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.model.User;

/**
 * 业务逻辑层-用户接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param dto
     */

    String login(LoginDto dto);

    /**
     * 根据令牌获取用户信息
     *
     * @param token 令牌
     * @return
     */
    User getByToken(String token);

}
