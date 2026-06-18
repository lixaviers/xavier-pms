package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dao.SystemConfigMapper;
import com.xavier.pms.dto.EmployeeCardConfigDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.SystemConfig;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.ISystemConfigService;
import com.xavier.pms.vo.EmployeeCardConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements ISystemConfigService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEmployeeCardConfig(EmployeeCardConfigDto configDto) {
        SystemConfig existing = getOne(SystemConfig.gw().eq(SystemConfig::getConfigKey, Constant.CONFIG_KEY_EMPLOYEE_CARD));

        String jsonValue = JSON.toJSONString(configDto);

        if (Objects.nonNull(existing)) {
            existing.setConfigValue(jsonValue);
            updateById(existing);
        } else {
            SystemConfig config = new SystemConfig();
            config.setConfigKey(Constant.CONFIG_KEY_EMPLOYEE_CARD);
            config.setConfigValue(jsonValue);
            config.setRemarks("个人卡片显示配置");
            save(config);
        }
    }

    @Override
    public EmployeeCardConfigVo getEmployeeCardConfig() {
        SystemConfig config = getOne(SystemConfig.gw().eq(SystemConfig::getConfigKey, Constant.CONFIG_KEY_EMPLOYEE_CARD));

        if (Objects.nonNull(config) && StrUtil.isNotBlank(config.getConfigValue())) {
            return JSON.parseObject(config.getConfigValue(), EmployeeCardConfigVo.class);
        }

        EmployeeCardConfigVo defaultConfig = new EmployeeCardConfigVo();
        defaultConfig.setShowMobile(true);
        defaultConfig.setShowEmail(true);
        defaultConfig.setShowDept(true);
        defaultConfig.setShowPosition(true);
        return defaultConfig;
    }

}
