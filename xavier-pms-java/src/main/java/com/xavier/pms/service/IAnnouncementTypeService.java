package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.AnnouncementTypeDto;
import com.xavier.pms.dto.AnnouncementTypeQueryDto;
import com.xavier.pms.vo.AnnouncementTypeVo;
import com.xavier.pms.model.AnnouncementType;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-公告类型接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IAnnouncementTypeService extends IService<AnnouncementType> {

    /**
     * 创建公告类型
     *
     * @param announcementTypeDto 公告类型入参
     * @return id
     */
    Long createAnnouncementType(AnnouncementTypeDto announcementTypeDto);

    /**
     * 更新公告类型
     *
     * @param announcementTypeDto 公告类型入参
     * @return 是否成功
     */
    Boolean updateAnnouncementType(AnnouncementTypeDto announcementTypeDto);

    /**
     * 删除公告类型
     *
     * @param idList id列表
     * @return 是否成功
     */
    Boolean deleteAnnouncementType(List<Long> idList);

    /**
     * 根据ID获取公告类型信息
     *
     * @param id
     * @return 公告类型信息
     */
    AnnouncementTypeVo getAnnouncementType(Long id);

    /**
     * 根据ID获取公告类型信息
     *
     * @param id
     * @return 公告类型信息
     */
    AnnouncementType getBaseAnnouncementType(Long id);

    /**
     * 分页查询公告类型信息
     *
     * @param announcementTypeQueryDto 公告类型查询入参
     * @return 公告类型信息
     */
    QueryResultVo<AnnouncementTypeVo> queryAnnouncementType(AnnouncementTypeQueryDto announcementTypeQueryDto);



}
