package com.xavier.pms.convertor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.AuditFormFlowDetailVo;
import com.xavier.pms.vo.AuditFormFlowVo;
import com.xavier.pms.vo.AuditFormVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 审批单对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class AuditFormConvertor {

    private static final BeanCopier beanCopierForAuditFormVo = BeanCopier.create(AuditForm.class, AuditFormVo.class, false);
    private static final BeanCopier beanCopierForAuditForm = BeanCopier.create(AuditFormDto.class, AuditForm.class, false);

    public static AuditFormVo toAuditFormVo(AuditForm auditForm) {
        if (Objects.isNull(auditForm)) {
            return null;
        }
        AuditFormVo auditFormVo = new AuditFormVo();
        beanCopierForAuditFormVo.copy(auditForm, auditFormVo, null);
        auditFormVo.setAuditStatusDesc(AuditStatusEnum.getDescByValue(auditForm.getAuditStatus()));
        return auditFormVo;
    }

    public static AuditForm toAuditForm(AuditFormDto auditFormDto) {
        AuditForm auditForm = new AuditForm();
        beanCopierForAuditForm.copy(auditFormDto, auditForm, null);
        return auditForm;
    }

    public static AuditFormFlowVo toAuditFormFlowVo(AuditFormFlow auditFormFlow, List<AuditFormFlowDetail> detailList) {
        AuditFormFlowVo vo = BeanUtil.beanCopy(auditFormFlow, AuditFormFlowVo.class);
        vo.setDetailList(BeanUtil.beanCopy(detailList, AuditFormFlowDetailVo.class));
        vo.setAuditStatus(detailList.get(0).getAuditStatus());
        List<AuditFormFlowDetail> remarks = detailList.stream().filter(bean -> StrUtil.isNotBlank(bean.getRemarks())).collect(Collectors.toList());
        List<AuditFormFlowDetail> dealTimes = detailList.stream().filter(bean -> Objects.nonNull(bean.getDealTime())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(remarks)) {
            vo.setRemarks(remarks.get(0).getRemarks());
        }
        if (CollUtil.isNotEmpty(dealTimes)) {
            vo.setDealTime(dealTimes.get(0).getDealTime());
        }
        return vo;
    }

    public static List<AuditFormVo> toAuditFormVoList(List<AuditForm> auditFormList) {
        if (Objects.isNull(auditFormList) || auditFormList.isEmpty()) {
            return new ArrayList<>();
        }
        List<AuditFormVo> auditFormInfoList = new ArrayList<AuditFormVo>(auditFormList.size());
        for (AuditForm auditForm : auditFormList) {
            auditFormInfoList.add(toAuditFormVo(auditForm));
        }
        return auditFormInfoList;
    }


}
