package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ScheduleAddDto;
import com.xavier.pms.dto.ScheduleQueryDto;
import com.xavier.pms.model.Schedule;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.vo.ScheduleVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 日程业务逻辑层接口
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IScheduleService extends IService<Schedule> {

    /**
     * 新增/编辑日程
     */
    void saveSchedule(ScheduleAddDto dto, Long userId);

    /**
     * 分页查询日程
     */
    QueryResultVo<ScheduleVo> querySchedule(ScheduleQueryDto dto, Long userId);

    /**
     * 根据id查询日程详情
     */
    ScheduleVo getScheduleDetail(Long id);

    /**
     * 删除日程
     */
    void deleteSchedule(Long id, Long userId);

    /**
     * 查询日历视图日程（按时间范围）
     */
    List<ScheduleVo> queryScheduleByTimeRange(LocalDateTime startTime, LocalDateTime endTime, Long userId);

    /**
     * 参与人接受/拒绝会议
     */
    void acceptSchedule(Long scheduleId, Long userId, Byte acceptStatus);

    /**
     * 查询今日待提醒日程
     */
    List<ScheduleVo> queryTodayRemindSchedules(Long userId);

    /**
     * 查询即将到来的日程（首页日程提醒）
     */
    List<ScheduleVo> queryUpcomingSchedules(Long userId);

}
