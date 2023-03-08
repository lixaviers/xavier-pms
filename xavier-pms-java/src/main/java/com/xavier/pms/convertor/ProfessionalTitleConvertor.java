package com.xavier.pms.convertor;

import com.xavier.pms.model.ProfessionalTitle;
import com.xavier.pms.dto.ProfessionalTitleDto;
import com.xavier.pms.vo.ProfessionalTitleVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 职称对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class ProfessionalTitleConvertor {

    private static final BeanCopier beanCopierForProfessionalTitleVo = BeanCopier.create(ProfessionalTitle.class, ProfessionalTitleVo.class, false);
    private static final BeanCopier beanCopierForProfessionalTitle = BeanCopier.create(ProfessionalTitleDto.class, ProfessionalTitle.class, false);

    public static ProfessionalTitleVo toProfessionalTitleVo(ProfessionalTitle professionalTitle) {
        if (Objects.isNull(professionalTitle)) {
            return null;
        }
        ProfessionalTitleVo professionalTitleVo = new ProfessionalTitleVo();
        beanCopierForProfessionalTitleVo.copy(professionalTitle, professionalTitleVo, null);
        return professionalTitleVo;
    }

    public static ProfessionalTitle toProfessionalTitle(ProfessionalTitleDto professionalTitleDto) {
        ProfessionalTitle professionalTitle = new ProfessionalTitle();
        beanCopierForProfessionalTitle.copy(professionalTitleDto, professionalTitle, null);
        return professionalTitle;
    }

    public static List<ProfessionalTitleVo> toProfessionalTitleVoList(List<ProfessionalTitle> professionalTitleList) {
        if (Objects.isNull(professionalTitleList) || professionalTitleList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProfessionalTitleVo> professionalTitleInfoList = new ArrayList<ProfessionalTitleVo>(professionalTitleList.size());
        for (ProfessionalTitle professionalTitle : professionalTitleList) {
            professionalTitleInfoList.add(toProfessionalTitleVo(professionalTitle));
        }
        return professionalTitleInfoList;
    }

}
