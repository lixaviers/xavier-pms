package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xavier.pms.model.ScheduleParticipant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日程参与人数据访问层
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Mapper
public interface ScheduleParticipantMapper extends BaseMapper<ScheduleParticipant> {

    /**
     * 根据日程id查询参与人列表
     */
    List<ScheduleParticipant> getByScheduleId(@Param("scheduleId") Long scheduleId);

    /**
     * 根据日程id删除参与人
     */
    int deleteByScheduleId(@Param("scheduleId") Long scheduleId);

}
