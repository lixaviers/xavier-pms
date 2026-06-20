package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ResignationAddDto;
import com.xavier.pms.dto.ResignationQueryDto;
import com.xavier.pms.model.Resignation;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.ResignationVo;

/**
 * 业务逻辑层-离职记录接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IResignationService extends IService<Resignation> {

    /**
     * 员工离职
     *
     * @param dto 离职入参
     */
    void resign(ResignationAddDto dto);

    /**
     * 分页查询离职记录
     *
     * @param dto 查询入参
     * @return 离职记录列表
     */
    QueryResultVo<ResignationVo> queryResignation(ResignationQueryDto dto);

    /**
     * 根据id查询离职详情
     *
     * @param id 离职记录id
     * @return 离职详情
     */
    ResignationVo getResignationDetail(Long id);

    /**
     * 根据id获取离职记录
     *
     * @param id 离职记录id
     * @return 离职记录实体
     */
    Resignation getBaseResignation(Long id);

}
