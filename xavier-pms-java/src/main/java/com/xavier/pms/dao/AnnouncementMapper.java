package com.xavier.pms.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.AnnouncementQueryDto;
import com.xavier.pms.model.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据持久层-公告管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    Page<Announcement> queryAnnouncement(@Param("dto") AnnouncementQueryDto queryDTO, Page<Announcement> page);

}
