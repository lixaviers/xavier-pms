package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.AuditFormMapper;
import com.xavier.pms.dao.DashboardMapper;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.Department;
import com.xavier.pms.model.User;
import com.xavier.pms.service.IAnnouncementService;
import com.xavier.pms.service.IDashboardService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.vo.DashboardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 业务逻辑层-工作台实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("dashboardService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class DashboardServiceImpl implements IDashboardService {

    @Resource
    private DashboardMapper dashboardMapper;

    @Resource
    private IUserService userService;

    @Resource
    private IAnnouncementService announcementService;

    @Override
    public DashboardVo getDashboardData(Long userId) {
        DashboardVo vo = new DashboardVo();

        // 审批统计
        // 待我审批：flow_detail 中 user_id=我 且 audit_status=3(审批中)
        vo.setPendingAuditCount(dashboardMapper.countPendingAudit(userId));

        // 我已处理：flow_detail 中 user_id=我 且 audit_status IN (4已通过, 5已拒绝)
        vo.setDealtCount(dashboardMapper.countDealt(userId));

        // 我发起的审批中：audit_form 中 user_id=我 且 audit_status=3(审批中)
        vo.setMyProcessingCount(dashboardMapper.countMyProcessing(userId));

        // 抄送我的：flow_detail 中 user_id=我 且 approval_type=cc 且 audit_status=7(自动抄送)
        vo.setCcUnreadCount(dashboardMapper.countCcUnread(userId));

        // 员工总数（正常状态）
        vo.setEmployeeCount(dashboardMapper.countEmployee());

        // 部门总数
        vo.setDepartmentCount(dashboardMapper.countDepartment());

        // 待办事项（待我审批的最近5条）
        vo.setTodoList(dashboardMapper.queryTodoList(userId));

        // 最近公告（最近5条已启用的公告）
        vo.setAnnouncementList(dashboardMapper.queryRecentAnnouncement());

        return vo;
    }

}
