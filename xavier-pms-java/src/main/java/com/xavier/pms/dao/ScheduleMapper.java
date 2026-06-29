package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.ScheduleQueryDto;
import com.xavier.pms.model.Schedule;
import com.xavier.pms.vo.ScheduleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 日程数据访问层
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    /**
     * 分页查询日程
     */
    Page<ScheduleVo> querySchedule(ScheduleQueryDto dto, Page<?> page, @Param("userId") Long userId);

    /**
     * 根据id查询日程详情
     */
    ScheduleVo getScheduleDetail(Long id);

    /**
     * 查询日历视图日程（不分页，按时间范围）
     */
    List<ScheduleVo> queryScheduleByTimeRange(@Param("startTime") LocalDateTime startTime,
                                               @Param("endTime") LocalDateTime endTime,
                                               @Param("userId") Long userId);

    /**
     * 查询今日待提醒日程
     */
    List<ScheduleVo> queryTodayRemindSchedules(@Param("userId") Long userId);

    /**
     * 查询即将到来的日程（首页日程提醒卡片）
     */
    List<ScheduleVo> queryUpcomingSchedules(@Param("userId") Long userId);

}
