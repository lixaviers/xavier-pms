package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.model.UserToken;

/**
 * 业务逻辑层-用户令牌接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IUserTokenService extends IService<UserToken> {

    /**
     * 创建用户令牌
     *
     * @param userId 用户id
     * @return id
     */
    String createUserToken(Long userId);


}
