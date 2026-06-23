package com.xavier.pms.dao;

import com.xavier.pms.vo.DashboardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作台数据访问层
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Mapper
public interface DashboardMapper {

    /**
     * 待我审批数量
     */
    Long countPendingAudit(@Param("userId") Long userId);

    /**
     * 我已处理数量
     */
    Long countDealt(@Param("userId") Long userId);

    /**
     * 我发起的审批中数量
     */
    Long countMyProcessing(@Param("userId") Long userId);

    /**
     * 抄送我的未读数量
     */
    Long countCcUnread(@Param("userId") Long userId);

    /**
     * 员工总数
     */
    Long countEmployee();

    /**
     * 部门总数
     */
    Long countDepartment();

    /**
     * 待办事项列表（最近5条）
     */
    List<DashboardVo.TodoItem> queryTodoList(@Param("userId") Long userId);

    /**
     * 最近公告列表（最近5条）
     */
    List<DashboardVo.AnnouncementItem> queryRecentAnnouncement();

}
