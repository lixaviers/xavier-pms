package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.PositionChangeAddDto;
import com.xavier.pms.dto.PositionChangeQueryDto;
import com.xavier.pms.model.PositionChange;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.PositionChangeVo;

/**
 * 业务逻辑层-员工职位/职称变动接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IPositionChangeService extends IService<PositionChange> {

    /**
     * 员工职位/职称变动
     *
     * @param dto 变动入参
     */
    void change(PositionChangeAddDto dto);

    /**
     * 分页查询变动记录
     *
     * @param dto 查询入参
     * @return 变动记录列表
     */
    QueryResultVo<PositionChangeVo> queryPositionChange(PositionChangeQueryDto dto);

    /**
     * 根据id查询变动详情
     *
     * @param id 变动记录id
     * @return 变动详情
     */
    PositionChangeVo getPositionChangeDetail(Long id);

    /**
     * 根据id获取变动记录
     *
     * @param id 变动记录id
     * @return 变动记录实体
     */
    PositionChange getBasePositionChange(Long id);

}
