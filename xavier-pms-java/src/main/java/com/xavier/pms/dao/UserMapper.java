package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.EmployeeQueryDto;
import com.xavier.pms.model.User;
import com.xavier.pms.vo.EmployeeCardVo;
import com.xavier.pms.vo.EmployeeListVo;
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

    // 查询最大员工号
    String getMaxEmployeeNumber();

    // 查询员工
    Page<EmployeeListVo> queryEmployee(@Param("dto") EmployeeQueryDto dto, Page<User> page);

    // 根据id查询员工卡片信息
    EmployeeCardVo getCard(@Param("id") Long id);

}
