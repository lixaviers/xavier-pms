package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.SealDto;
import com.xavier.pms.dto.SealQueryDto;
import com.xavier.pms.vo.SealVo;
import com.xavier.pms.model.Seal;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-印章接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface ISealService extends IService<Seal> {

    /**
     * 创建印章
     *
     * @param sealDto 印章入参
     * @return id
     */
    Long createSeal(SealDto sealDto);

    /**
     * 更新印章
     *
     * @param sealDto 印章入参
     * @return 是否成功
     */
    Boolean updateSeal(SealDto sealDto);

    /**
     * 删除印章
     *
     * @param idList id列表
     * @return 是否成功
     */
    Boolean deleteSeal(List<Long> idList);

    /**
     * 根据ID获取印章信息
     *
     * @param id
     * @return 印章信息
     */
    SealVo getSeal(Long id);

    /**
     * 根据ID获取印章信息
     *
     * @param id
     * @return 印章信息
     */
    Seal getBaseSeal(Long id);

    /**
     * 分页查询印章信息
     *
     * @param sealQueryDto 印章查询入参
     * @return 印章信息
     */
    QueryResultVo<SealVo> querySeal(SealQueryDto sealQueryDto);



}
