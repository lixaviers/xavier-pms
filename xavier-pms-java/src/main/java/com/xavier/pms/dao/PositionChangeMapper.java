package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.PositionChangeQueryDto;
import com.xavier.pms.model.PositionChange;
import com.xavier.pms.vo.PositionChangeVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工职位/职称变动记录数据访问层
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Mapper
public interface PositionChangeMapper extends BaseMapper<PositionChange> {

    /**
     * 分页查询变动记录
     */
    Page<PositionChangeVo> queryPositionChange(PositionChangeQueryDto dto, Page<?> page);

    /**
     * 根据id查询变动详情
     */
    PositionChangeVo getPositionChangeDetail(Long id);

}
