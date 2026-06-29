package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.ScheduleMapper;
import com.xavier.pms.dao.ScheduleParticipantMapper;
import com.xavier.pms.dto.ScheduleAddDto;
import com.xavier.pms.dto.ScheduleQueryDto;
import com.xavier.pms.enums.AcceptStatusEnum;
import com.xavier.pms.enums.RemindTypeEnum;
import com.xavier.pms.enums.ScheduleTypeEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Schedule;
import com.xavier.pms.model.ScheduleParticipant;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IScheduleService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ScheduleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 日程业务逻辑层实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("scheduleService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    private final ScheduleParticipantMapper scheduleParticipantMapper;
    private final IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSchedule(ScheduleAddDto dto, Long userId) {
        // 校验日程类型
        if (!ScheduleTypeEnum.exists(dto.getScheduleType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "日程类型");
        }

        // 校验时间
        if (dto.getEndTime().isBefore(dto.getStartTime()) || dto.getEndTime().isEqual(dto.getStartTime())) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "结束时间必须晚于开始时间");
        }

        // 校验提醒方式
        if (dto.getRemindType() != null && !RemindTypeEnum.exists(dto.getRemindType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "提醒方式");
        }

        Schedule schedule;
        if (dto.getId() != null) {
            // 编辑
            schedule = this.getById(dto.getId());
            if (schedule == null || schedule.getIsDeleted()) {
                throw new ServiceException(ResultCode.COMMON_MESSAGE, "日程不存在");
            }
            if (!schedule.getUserId().equals(userId)) {
                throw new ServiceException(ResultCode.COMMON_MESSAGE, "只能编辑自己的日程");
            }
            BeanUtil.beanCopy(dto, schedule);
            schedule.setUserId(userId);
            // 删除原有参与人
            scheduleParticipantMapper.deleteByScheduleId(dto.getId());
        } else {
            // 新增
            schedule = new Schedule();
            BeanUtil.beanCopy(dto, schedule);
            schedule.setUserId(userId);
        }

        // 设置默认值
        if (schedule.getIsAllDay() == null) {
            schedule.setIsAllDay(false);
        }
        if (schedule.getRemindType() == null) {
            schedule.setRemindType((byte) 0);
        }
        if (schedule.getIsPublic() == null) {
            schedule.setIsPublic(false);
        }
        if (schedule.getColor() == null || schedule.getColor().isEmpty()) {
            schedule.setColor("#409EFF");
        }

        this.saveOrUpdate(schedule);

        // 保存参与人（会议类型）
        if (dto.getScheduleType() == ScheduleTypeEnum.MEETING.getValue()
                && !CollectionUtils.isEmpty(dto.getParticipantIds())) {
            List<ScheduleParticipant> participants = new ArrayList<>();
            for (Long participantId : dto.getParticipantIds()) {
                User participant = userService.getBaseUser(participantId);
                ScheduleParticipant sp = new ScheduleParticipant();
                sp.setScheduleId(schedule.getId());
                sp.setUserId(participantId);
                sp.setNickName(participant.getNickName());
                sp.setAcceptStatus((byte) 0); // 待确认
                participants.add(sp);
            }
            for (ScheduleParticipant sp : participants) {
                scheduleParticipantMapper.insert(sp);
            }
        }
    }

    @Override
    public QueryResultVo<ScheduleVo> querySchedule(ScheduleQueryDto dto, Long userId) {
        Page<ScheduleVo> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        Page<ScheduleVo> result = baseMapper.querySchedule(dto, page, userId);

        // 填充枚举描述和参与人
        for (ScheduleVo vo : result.getRecords()) {
            fillScheduleVo(vo);
        }

        QueryResultVo<ScheduleVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, ScheduleVo.class);
        queryResultVo.setRecords(result.getRecords());
        return queryResultVo;
    }

    @Override
    public ScheduleVo getScheduleDetail(Long id) {
        ScheduleVo vo = baseMapper.getScheduleDetail(id);
        if (vo == null) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "日程不存在");
        }
        fillScheduleVo(vo);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = this.getById(id);
        if (schedule == null || schedule.getIsDeleted()) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "日程不存在");
        }
        if (!schedule.getUserId().equals(userId)) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "只能删除自己的日程");
        }
        this.removeById(id);
        scheduleParticipantMapper.deleteByScheduleId(id);
    }

    @Override
    public List<ScheduleVo> queryScheduleByTimeRange(LocalDateTime startTime, LocalDateTime endTime, Long userId) {
        List<ScheduleVo> list = baseMapper.queryScheduleByTimeRange(startTime, endTime, userId);
        for (ScheduleVo vo : list) {
            fillScheduleVo(vo);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void acceptSchedule(Long scheduleId, Long userId, Byte acceptStatus) {
        if (!AcceptStatusEnum.exists(acceptStatus) || acceptStatus == 0) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "接受状态");
        }

        // 查找参与人记录
        ScheduleParticipant sp = scheduleParticipantMapper.selectOne(
                ScheduleParticipant.gw()
                        .eq(ScheduleParticipant::getScheduleId, scheduleId)
                        .eq(ScheduleParticipant::getUserId, userId)
                        .eq(ScheduleParticipant::getIsDeleted, false)
        );
        if (sp == null) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "您不是该会议的参与人");
        }
        sp.setAcceptStatus(acceptStatus);
        scheduleParticipantMapper.updateById(sp);
    }

    @Override
    public List<ScheduleVo> queryTodayRemindSchedules(Long userId) {
        List<ScheduleVo> list = baseMapper.queryTodayRemindSchedules(userId);
        for (ScheduleVo vo : list) {
            fillScheduleVo(vo);
        }
        return list;
    }

    @Override
    public List<ScheduleVo> queryUpcomingSchedules(Long userId) {
        List<ScheduleVo> list = baseMapper.queryUpcomingSchedules(userId);
        for (ScheduleVo vo : list) {
            fillScheduleVo(vo);
        }
        return list;
    }

    /**
     * 填充日程VO的枚举描述和参与人
     */
    private void fillScheduleVo(ScheduleVo vo) {
        vo.setScheduleTypeDesc(ScheduleTypeEnum.getDescByValue(vo.getScheduleType()));
        vo.setRemindTypeDesc(RemindTypeEnum.getDescByValue(vo.getRemindType()));

        // 查询参与人
        List<ScheduleParticipant> participants = scheduleParticipantMapper.getByScheduleId(vo.getId());
        List<ScheduleVo.ParticipantItem> items = new ArrayList<>();
        for (ScheduleParticipant sp : participants) {
            ScheduleVo.ParticipantItem item = new ScheduleVo.ParticipantItem();
            item.setId(sp.getId());
            item.setUserId(sp.getUserId());
            item.setNickName(sp.getNickName());
            item.setAcceptStatus(sp.getAcceptStatus());
            item.setAcceptStatusDesc(AcceptStatusEnum.getDescByValue(sp.getAcceptStatus()));
            items.add(item);
        }
        vo.setParticipants(items);
    }

}
