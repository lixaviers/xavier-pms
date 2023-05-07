package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xavier.pms.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据持久层-菜单管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getUserMenuList(@Param("roleIdList") List<Long> roleIdList, @Param("typeList") List<String> typeList);

}
