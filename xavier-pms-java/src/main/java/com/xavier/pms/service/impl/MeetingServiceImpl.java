package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.convertor.MeetingConvertor;
import com.xavier.pms.dto.MeetingQueryDto;
import com.xavier.pms.model.Meeting;
import com.xavier.pms.dto.MeetingDto;
import com.xavier.pms.vo.MeetingVo;
import com.xavier.pms.dao.MeetingMapper;
import com.xavier.pms.service.IMeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-会议室实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("meetingService")
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createMeeting(MeetingDto meetingDto) {
        Meeting meeting = MeetingConvertor.toMeeting(meetingDto);
        meeting.setId(null);
        super.save(meeting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void updateMeeting(MeetingDto meetingDto) {
        getBaseMeeting(meetingDto.getId());
        Meeting meeting  = MeetingConvertor.toMeeting(meetingDto);
        super.updateById(meeting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void deleteMeeting(List<Long> idList) {
        super.removeBatchByIds(idList);
    }

    @Override
    public MeetingVo getMeeting(Long id) {
        return MeetingConvertor.toMeetingVo(getBaseMeeting(id));
    }


    @Override
    public Meeting getBaseMeeting(Long id) {
        Meeting meeting = super.getById(id);
        if(Objects.isNull(meeting)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "会议室");
        }
        return meeting;
    }

    @Override
    public QueryResultVo<MeetingVo> queryMeeting(MeetingQueryDto queryDTO) {
        Page<Meeting> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<Meeting> wrapper = Meeting.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(Meeting::getId, queryDTO.getId());
        }
        if (StrUtil.isNotBlank(queryDTO.getMeetingName())) {
            // 会议室名称不为空
            wrapper.like(Meeting::getMeetingName, queryDTO.getMeetingName());
        }
        if (StrUtil.isNotBlank(queryDTO.getPosition())) {
            // 位置不为空
            wrapper.like(Meeting::getPosition, queryDTO.getPosition());
        }
        if (Objects.nonNull(queryDTO.getIsEnable())) {
            // 是否启用不为空
            wrapper.eq(Meeting::getIsEnable, queryDTO.getIsEnable());
        }
        if (StrUtil.isNotBlank(queryDTO.getRemarks())) {
            // 备注不为空
            wrapper.like(Meeting::getRemarks, queryDTO.getRemarks());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(Meeting::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(Meeting::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(Meeting::getId);
        Page<Meeting> result = super.page(page, wrapper);
        QueryResultVo<MeetingVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, MeetingVo.class);
        queryResultVo.setRecords(MeetingConvertor.toMeetingVoList(result.getRecords()));
        return queryResultVo;
    }

}
