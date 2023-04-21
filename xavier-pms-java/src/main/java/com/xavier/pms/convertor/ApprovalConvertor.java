package com.xavier.pms.convertor;

import com.alibaba.fastjson2.JSON;
import com.xavier.pms.dto.ApprovalDto;
import com.xavier.pms.model.Approval;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ApprovalDetailVo;
import com.xavier.pms.vo.ApprovalProcessJsonVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Objects;

/**
 * 审批对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ApprovalConvertor {

    private static final BeanCopier beanCopierForApproval = BeanCopier.create(ApprovalDto.class, Approval.class, false);

    public static ApprovalDetailVo toApprovalDetailVo(Approval approval) {
        if (Objects.isNull(approval)) {
            return null;
        }
        ApprovalDetailVo vo = BeanUtil.beanCopy(approval, ApprovalDetailVo.class);
        vo.setProcessList(JSON.parseArray(approval.getProcess(), ApprovalProcessJsonVo.class));
        return vo;
    }

    public static Approval toApproval(ApprovalDto dto) {
        Approval approval = new Approval();
        beanCopierForApproval.copy(dto, approval, null);
        approval.setProcess(JSON.toJSONString(dto.getProcessList()));
        for (ApprovalProcessJsonVo jsonVo : dto.getProcessList()) {
            if (Objects.equals(jsonVo.getType(), "start")) {
                // 提交
                approval.setSubmitType(jsonVo.getSubmitType());
            }
        }
        return approval;
    }


}
