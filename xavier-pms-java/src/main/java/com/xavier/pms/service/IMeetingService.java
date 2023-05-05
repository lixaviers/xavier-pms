package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.MeetingDto;
import com.xavier.pms.dto.MeetingQueryDto;
import com.xavier.pms.vo.MeetingVo;
import com.xavier.pms.model.Meeting;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-会议室接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IMeetingService extends IService<Meeting> {

    /**
     * 创建会议室
     *
     * @param meetingDto 会议室入参
     */
    void createMeeting(MeetingDto meetingDto);

    /**
     * 更新会议室
     *
     * @param meetingDto 会议室入参
     */
    void updateMeeting(MeetingDto meetingDto);

    /**
     * 删除会议室
     *
     * @param idList id列表
     */
    void deleteMeeting(List<Long> idList);

    /**
     * 根据ID获取会议室信息
     *
     * @param id
     * @return 会议室信息
     */
    MeetingVo getMeeting(Long id);

    /**
     * 根据ID获取会议室信息
     *
     * @param id
     * @return 会议室信息
     */
    Meeting getBaseMeeting(Long id);

    /**
     * 分页查询会议室信息
     *
     * @param meetingQueryDto 会议室查询入参
     * @return 会议室信息
     */
    QueryResultVo<MeetingVo> queryMeeting(MeetingQueryDto meetingQueryDto);



}
