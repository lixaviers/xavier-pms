package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xavier.pms.dto.ApplicationGroupQueryDto;
import com.xavier.pms.model.ApplicationGroup;
import com.xavier.pms.vo.ApplicationGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据持久层-应用分组管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface ApplicationGroupMapper extends BaseMapper<ApplicationGroup> {

    List<ApplicationGroupVo> queryApplicationGroup(@Param("dto") ApplicationGroupQueryDto queryDTO);

}
