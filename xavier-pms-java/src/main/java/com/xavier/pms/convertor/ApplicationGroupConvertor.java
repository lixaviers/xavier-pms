package com.xavier.pms.convertor;

import com.xavier.pms.model.ApplicationGroup;
import com.xavier.pms.dto.ApplicationGroupDto;
import com.xavier.pms.vo.ApplicationGroupVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 应用分组对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ApplicationGroupConvertor {

    private static final BeanCopier beanCopierForApplicationGroupVo = BeanCopier.create(ApplicationGroup.class, ApplicationGroupVo.class, false);
    private static final BeanCopier beanCopierForApplicationGroup = BeanCopier.create(ApplicationGroupDto.class, ApplicationGroup.class, false);

    public static ApplicationGroupVo toApplicationGroupVo(ApplicationGroup applicationGroup) {
        if (Objects.isNull(applicationGroup)) {
            return null;
        }
        ApplicationGroupVo applicationGroupVo = new ApplicationGroupVo();
        beanCopierForApplicationGroupVo.copy(applicationGroup, applicationGroupVo, null);
        return applicationGroupVo;
    }

    public static ApplicationGroup toApplicationGroup(ApplicationGroupDto applicationGroupDto) {
        ApplicationGroup applicationGroup = new ApplicationGroup();
        beanCopierForApplicationGroup.copy(applicationGroupDto, applicationGroup, null);
        return applicationGroup;
    }

    public static List<ApplicationGroupVo> toApplicationGroupVoList(List<ApplicationGroup> applicationGroupList) {
        if (Objects.isNull(applicationGroupList) || applicationGroupList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ApplicationGroupVo> applicationGroupInfoList = new ArrayList<ApplicationGroupVo>(applicationGroupList.size());
        for (ApplicationGroup applicationGroup : applicationGroupList) {
            applicationGroupInfoList.add(toApplicationGroupVo(applicationGroup));
        }
        return applicationGroupInfoList;
    }

}
