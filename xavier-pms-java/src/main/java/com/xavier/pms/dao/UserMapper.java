package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xavier.pms.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * 数据持久层-用户管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface UserMapper extends BaseMapper<User> {

    // 根据令牌获取用户信息
    User getByToken(@Param("token") String token);

}
