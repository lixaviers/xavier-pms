package com.xavier.pms.convertor;

import com.xavier.pms.model.Announcement;
import com.xavier.pms.dto.AnnouncementDto;
import com.xavier.pms.vo.AnnouncementVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 公告对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class AnnouncementConvertor {

    private static final BeanCopier beanCopierForAnnouncementVo = BeanCopier.create(Announcement.class, AnnouncementVo.class, false);
    private static final BeanCopier beanCopierForAnnouncement = BeanCopier.create(AnnouncementDto.class, Announcement.class, false);

    public static AnnouncementVo toAnnouncementVo(Announcement announcement) {
        if (Objects.isNull(announcement)) {
            return null;
        }
        AnnouncementVo announcementVo = new AnnouncementVo();
        beanCopierForAnnouncementVo.copy(announcement, announcementVo, null);
        return announcementVo;
    }

    public static Announcement toAnnouncement(AnnouncementDto announcementDto) {
        Announcement announcement = new Announcement();
        beanCopierForAnnouncement.copy(announcementDto, announcement, null);
        return announcement;
    }

    public static List<AnnouncementVo> toAnnouncementVoList(List<Announcement> announcementList) {
        if (Objects.isNull(announcementList) || announcementList.isEmpty()) {
            return new ArrayList<>();
        }
        List<AnnouncementVo> announcementInfoList = new ArrayList<AnnouncementVo>(announcementList.size());
        for (Announcement announcement : announcementList) {
            announcementInfoList.add(toAnnouncementVo(announcement));
        }
        return announcementInfoList;
    }

}
