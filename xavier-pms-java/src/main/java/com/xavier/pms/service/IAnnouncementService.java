package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.AnnouncementDto;
import com.xavier.pms.dto.AnnouncementQueryDto;
import com.xavier.pms.vo.AnnouncementVo;
import com.xavier.pms.model.Announcement;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-公告接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IAnnouncementService extends IService<Announcement> {

    /**
     * 创建公告
     *
     * @param announcementDto 公告入参
     * @return id
     */
    Long createAnnouncement(AnnouncementDto announcementDto);

    /**
     * 更新公告
     *
     * @param announcementDto 公告入参
     * @return 是否成功
     */
    Boolean updateAnnouncement(AnnouncementDto announcementDto);

    /**
     * 删除公告
     *
     * @param idList id列表
     * @return 是否成功
     */
    Boolean deleteAnnouncement(List<Long> idList);

    /**
     * 根据ID获取公告信息
     *
     * @param id
     * @return 公告信息
     */
    AnnouncementVo getAnnouncement(Long id);

    /**
     * 根据ID获取公告信息
     *
     * @param id
     * @return 公告信息
     */
    Announcement getBaseAnnouncement(Long id);

    /**
     * 分页查询公告信息
     *
     * @param announcementQueryDto 公告查询入参
     * @return 公告信息
     */
    QueryResultVo<AnnouncementVo> queryAnnouncement(AnnouncementQueryDto announcementQueryDto);



}
