package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.UserQueryDto;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.UserVo;

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

    /**
     * 创建用户
     *
     * @param userDto 用户入参
     * @return id
     */
    Long createUser(EmployeeAddDto userDto);

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return 用户信息
     */
    UserVo getUser(Long id);

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return 用户信息
     */
    User getBaseUser(Long id);

    /**
     * 分页查询用户信息
     *
     * @param userQueryDto 用户查询入参
     * @return 用户信息
     */
    QueryResultVo<UserVo> queryUser(UserQueryDto userQueryDto);


}
