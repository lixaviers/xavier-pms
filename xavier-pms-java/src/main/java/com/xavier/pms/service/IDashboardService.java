package com.xavier.pms.service;

import com.xavier.pms.vo.DashboardVo;

/**
 * 业务逻辑层-工作台接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IDashboardService {

    /**
     * 获取工作台数据
     *
     * @param userId 登录用户id
     * @return 工作台数据
     */
    DashboardVo getDashboardData(Long userId);

}
