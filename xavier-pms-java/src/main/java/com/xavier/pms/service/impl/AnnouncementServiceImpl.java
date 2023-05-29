package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.AnnouncementConvertor;
import com.xavier.pms.dao.AnnouncementMapper;
import com.xavier.pms.dto.AnnouncementDto;
import com.xavier.pms.dto.AnnouncementQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Announcement;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IAnnouncementService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.AnnouncementVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-公告实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("announcementService")
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createAnnouncement(AnnouncementDto announcementDto) {
        Announcement announcement = AnnouncementConvertor.toAnnouncement(announcementDto);
        announcement.setId(null);
        super.save(announcement);
        return announcement.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateAnnouncement(AnnouncementDto announcementDto) {
        getBaseAnnouncement(announcementDto.getId());
        Announcement announcement = AnnouncementConvertor.toAnnouncement(announcementDto);
        return super.updateById(announcement);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteAnnouncement(List<Long> idList) {
        return super.removeBatchByIds(idList);
    }

    @Override
    public AnnouncementVo getAnnouncement(Long id) {
        return AnnouncementConvertor.toAnnouncementVo(getBaseAnnouncement(id));
    }


    @Override
    public Announcement getBaseAnnouncement(Long id) {
        Announcement announcement = super.getById(id);
        if (Objects.isNull(announcement)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "公告");
        }
        return announcement;
    }

    @Override
    public QueryResultVo<AnnouncementVo> queryAnnouncement(AnnouncementQueryDto queryDTO) {
        Page<Announcement> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        Page<Announcement> result = baseMapper.queryAnnouncement(queryDTO, page);
        QueryResultVo<AnnouncementVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, AnnouncementVo.class);
        queryResultVo.setRecords(AnnouncementConvertor.toAnnouncementVoList(result.getRecords()));
        return queryResultVo;
    }

}
