package com.xavier.pms.convertor;

import com.xavier.pms.model.AnnouncementType;
import com.xavier.pms.dto.AnnouncementTypeDto;
import com.xavier.pms.vo.AnnouncementTypeVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 公告类型对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class AnnouncementTypeConvertor {

    private static final BeanCopier beanCopierForAnnouncementTypeVo = BeanCopier.create(AnnouncementType.class, AnnouncementTypeVo.class, false);
    private static final BeanCopier beanCopierForAnnouncementType = BeanCopier.create(AnnouncementTypeDto.class, AnnouncementType.class, false);

    public static AnnouncementTypeVo toAnnouncementTypeVo(AnnouncementType announcementType) {
        if (Objects.isNull(announcementType)) {
            return null;
        }
        AnnouncementTypeVo announcementTypeVo = new AnnouncementTypeVo();
        beanCopierForAnnouncementTypeVo.copy(announcementType, announcementTypeVo, null);
        return announcementTypeVo;
    }

    public static AnnouncementType toAnnouncementType(AnnouncementTypeDto announcementTypeDto) {
        AnnouncementType announcementType = new AnnouncementType();
        beanCopierForAnnouncementType.copy(announcementTypeDto, announcementType, null);
        return announcementType;
    }

    public static List<AnnouncementTypeVo> toAnnouncementTypeVoList(List<AnnouncementType> announcementTypeList) {
        if (Objects.isNull(announcementTypeList) || announcementTypeList.isEmpty()) {
            return new ArrayList<>();
        }
        List<AnnouncementTypeVo> announcementTypeInfoList = new ArrayList<AnnouncementTypeVo>(announcementTypeList.size());
        for (AnnouncementType announcementType : announcementTypeList) {
            announcementTypeInfoList.add(toAnnouncementTypeVo(announcementType));
        }
        return announcementTypeInfoList;
    }

}
