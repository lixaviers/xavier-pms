package com.xavier.pms.convertor;

import com.xavier.pms.dto.AuditFormDto;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditForm;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.AuditFormFlowVo;
import com.xavier.pms.vo.AuditFormVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static AuditFormFlowVo toAuditFormFlowVo(AuditFormFlow auditFormFlow) {
        return BeanUtil.beanCopy(auditFormFlow, AuditFormFlowVo.class);
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
