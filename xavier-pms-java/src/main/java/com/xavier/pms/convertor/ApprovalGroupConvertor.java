package com.xavier.pms.convertor;

import com.xavier.pms.model.ApprovalGroup;
import com.xavier.pms.dto.ApprovalGroupDto;
import com.xavier.pms.vo.ApprovalGroupVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 审批分组对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ApprovalGroupConvertor {

    private static final BeanCopier beanCopierForApprovalGroupVo = BeanCopier.create(ApprovalGroup.class, ApprovalGroupVo.class, false);
    private static final BeanCopier beanCopierForApprovalGroup = BeanCopier.create(ApprovalGroupDto.class, ApprovalGroup.class, false);

    public static ApprovalGroupVo toApprovalGroupVo(ApprovalGroup approvalGroup) {
        if (Objects.isNull(approvalGroup)) {
            return null;
        }
        ApprovalGroupVo approvalGroupVo = new ApprovalGroupVo();
        beanCopierForApprovalGroupVo.copy(approvalGroup, approvalGroupVo, null);
        return approvalGroupVo;
    }

    public static ApprovalGroup toApprovalGroup(ApprovalGroupDto approvalGroupDto) {
        ApprovalGroup approvalGroup = new ApprovalGroup();
        beanCopierForApprovalGroup.copy(approvalGroupDto, approvalGroup, null);
        return approvalGroup;
    }

    public static List<ApprovalGroupVo> toApprovalGroupVoList(List<ApprovalGroup> approvalGroupList) {
        if (Objects.isNull(approvalGroupList) || approvalGroupList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ApprovalGroupVo> approvalGroupInfoList = new ArrayList<ApprovalGroupVo>(approvalGroupList.size());
        for (ApprovalGroup approvalGroup : approvalGroupList) {
            approvalGroupInfoList.add(toApprovalGroupVo(approvalGroup));
        }
        return approvalGroupInfoList;
    }

}
