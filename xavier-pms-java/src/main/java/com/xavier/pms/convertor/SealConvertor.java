package com.xavier.pms.convertor;

import com.xavier.pms.model.Seal;
import com.xavier.pms.dto.SealDto;
import com.xavier.pms.vo.SealVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 印章对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class SealConvertor {

    private static final BeanCopier beanCopierForSealVo = BeanCopier.create(Seal.class, SealVo.class, false);
    private static final BeanCopier beanCopierForSeal = BeanCopier.create(SealDto.class, Seal.class, false);

    public static SealVo toSealVo(Seal seal) {
        if (Objects.isNull(seal)) {
            return null;
        }
        SealVo sealVo = new SealVo();
        beanCopierForSealVo.copy(seal, sealVo, null);
        return sealVo;
    }

    public static Seal toSeal(SealDto sealDto) {
        Seal seal = new Seal();
        beanCopierForSeal.copy(sealDto, seal, null);
        return seal;
    }

    public static List<SealVo> toSealVoList(List<Seal> sealList) {
        if (Objects.isNull(sealList) || sealList.isEmpty()) {
            return new ArrayList<>();
        }
        List<SealVo> sealInfoList = new ArrayList<SealVo>(sealList.size());
        for (Seal seal : sealList) {
            sealInfoList.add(toSealVo(seal));
        }
        return sealInfoList;
    }

}
