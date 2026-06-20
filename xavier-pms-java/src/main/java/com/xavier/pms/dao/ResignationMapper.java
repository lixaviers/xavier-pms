package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.ResignationQueryDto;
import com.xavier.pms.model.Resignation;
import com.xavier.pms.vo.ResignationVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 离职记录数据访问层
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Mapper
public interface ResignationMapper extends BaseMapper<Resignation> {

    /**
     * 分页查询离职记录
     */
    Page<ResignationVo> queryResignation(ResignationQueryDto dto, Page<?> page);

    /**
     * 根据id查询离职详情
     */
    ResignationVo getResignationDetail(Long id);

}
