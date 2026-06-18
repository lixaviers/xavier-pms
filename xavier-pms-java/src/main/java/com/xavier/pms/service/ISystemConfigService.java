package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.EmployeeCardConfigDto;
import com.xavier.pms.model.SystemConfig;
import com.xavier.pms.vo.EmployeeCardConfigVo;

public interface ISystemConfigService extends IService<SystemConfig> {

    /**
     * 保存个人卡片配置
     *
     * @param configDto 个人卡片配置信息
     */
    void saveEmployeeCardConfig(EmployeeCardConfigDto configDto);

    /**
     * 获取个人卡片配置
     *
     * @return 个人卡片配置信息
     */
    EmployeeCardConfigVo getEmployeeCardConfig();

}
