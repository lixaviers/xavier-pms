package com.xavier.pms.convertor;

import com.xavier.pms.model.Application;
import com.xavier.pms.dto.ApplicationDto;
import com.xavier.pms.vo.ApplicationVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 应用对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ApplicationConvertor {

    private static final BeanCopier beanCopierForApplicationVo = BeanCopier.create(Application.class, ApplicationVo.class, false);
    private static final BeanCopier beanCopierForApplication = BeanCopier.create(ApplicationDto.class, Application.class, false);

    public static ApplicationVo toApplicationVo(Application application) {
        if (Objects.isNull(application)) {
            return null;
        }
        ApplicationVo applicationVo = new ApplicationVo();
        beanCopierForApplicationVo.copy(application, applicationVo, null);
        return applicationVo;
    }

    public static Application toApplication(ApplicationDto applicationDto) {
        Application application = new Application();
        beanCopierForApplication.copy(applicationDto, application, null);
        return application;
    }

    public static List<ApplicationVo> toApplicationVoList(List<Application> applicationList) {
        if (Objects.isNull(applicationList) || applicationList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ApplicationVo> applicationInfoList = new ArrayList<ApplicationVo>(applicationList.size());
        for (Application application : applicationList) {
            applicationInfoList.add(toApplicationVo(application));
        }
        return applicationInfoList;
    }

}
