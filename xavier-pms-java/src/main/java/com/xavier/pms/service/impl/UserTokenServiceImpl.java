package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.UserTokenMapper;
import com.xavier.pms.model.UserToken;
import com.xavier.pms.service.IUserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * 业务逻辑层-用户令牌实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("userTokenService")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String createUserToken(Long userId) {
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setToken(UUID.randomUUID().toString().replace("-", ""));
        // 4个小时有效期
        userToken.setExpirationTime(LocalDateTime.now().plusHours(4));
        super.save(userToken);
        return userToken.getToken();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void deleteByToken(String token) {
        super.remove(UserToken.gw().eq(UserToken::getToken, token));
    }

}
