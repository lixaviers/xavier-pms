package com.xavier.pms.controller;

import com.xavier.pms.result.Result;
import com.xavier.pms.service.IDashboardService;
import com.xavier.pms.vo.DashboardVo;
import com.xavier.pms.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 请求处理层-工作台管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@RequestMapping("dashboard")
@RestController
@Tag(name = "工作台接口")
public class DashboardController extends CommonController {

    @Resource
    private IDashboardService dashboardService;

    @Operation(summary = "获取工作台数据", description = "获取当前登录用户的工作台数据，包含审批统计、待办事项、最近公告等")
    @GetMapping("data")
    public Result<DashboardVo> data() {
        UserInfoVo loginUser = getLoginUser();
        return Result.ok(dashboardService.getDashboardData(loginUser.getId()));
    }

}
