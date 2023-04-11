package com.xavier.pms.convertor;

import com.xavier.pms.model.Meeting;
import com.xavier.pms.dto.MeetingDto;
import com.xavier.pms.vo.MeetingVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 会议室对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class MeetingConvertor {

    private static final BeanCopier beanCopierForMeetingVo = BeanCopier.create(Meeting.class, MeetingVo.class, false);
    private static final BeanCopier beanCopierForMeeting = BeanCopier.create(MeetingDto.class, Meeting.class, false);

    public static MeetingVo toMeetingVo(Meeting meeting) {
        if (Objects.isNull(meeting)) {
            return null;
        }
        MeetingVo meetingVo = new MeetingVo();
        beanCopierForMeetingVo.copy(meeting, meetingVo, null);
        return meetingVo;
    }

    public static Meeting toMeeting(MeetingDto meetingDto) {
        Meeting meeting = new Meeting();
        beanCopierForMeeting.copy(meetingDto, meeting, null);
        return meeting;
    }

    public static List<MeetingVo> toMeetingVoList(List<Meeting> meetingList) {
        if (Objects.isNull(meetingList) || meetingList.isEmpty()) {
            return new ArrayList<>();
        }
        List<MeetingVo> meetingInfoList = new ArrayList<MeetingVo>(meetingList.size());
        for (Meeting meeting : meetingList) {
            meetingInfoList.add(toMeetingVo(meeting));
        }
        return meetingInfoList;
    }

}
