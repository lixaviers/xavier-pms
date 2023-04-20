package com.xavier.pms.convertor;

import com.alibaba.fastjson2.JSON;
import com.xavier.pms.dto.ApplicationDto;
import com.xavier.pms.model.Application;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ApplicationDetailVo;
import com.xavier.pms.vo.ApplicationProcessJsonVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Objects;

/**
 * 应用对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ApplicationConvertor {

    private static final BeanCopier beanCopierForApplication = BeanCopier.create(ApplicationDto.class, Application.class, false);

    public static ApplicationDetailVo toApplicationDetailVo(Application application) {
        if (Objects.isNull(application)) {
            return null;
        }
        ApplicationDetailVo vo = BeanUtil.beanCopy(application, ApplicationDetailVo.class);
        vo.setProcessList(JSON.parseArray(application.getProcess(), ApplicationProcessJsonVo.class));
        return vo;
    }

    public static Application toApplication(ApplicationDto applicationDto) {
        Application application = new Application();
        beanCopierForApplication.copy(applicationDto, application, null);
        application.setProcess(JSON.toJSONString(applicationDto.getProcessList()));
        for (ApplicationProcessJsonVo jsonVo : applicationDto.getProcessList()) {
            if (Objects.equals(jsonVo.getType(), "start")) {
                // 提交
                application.setSubmitType(jsonVo.getSubmitType());
            }
        }
        return application;
    }


}
