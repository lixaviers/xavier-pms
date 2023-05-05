package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.ProfessionalTitleDto;
import com.xavier.pms.dto.ProfessionalTitleQueryDto;
import com.xavier.pms.vo.ProfessionalTitleVo;
import com.xavier.pms.model.ProfessionalTitle;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-职称接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IProfessionalTitleService extends IService<ProfessionalTitle> {

    /**
     * 删除职称
     *
     * @param idList
     */
    void deleteProfessionalTitle(List<Long> idList);

    /**
     * 创建职称
     *
     * @param professionalTitleDto 职称入参
     */
    void createProfessionalTitle(ProfessionalTitleDto professionalTitleDto);

    /**
     * 更新职称
     *
     * @param professionalTitleDto 职称入参
     */
    void updateProfessionalTitle(ProfessionalTitleDto professionalTitleDto);

    /**
     * 根据ID获取职称信息
     *
     * @param id
     * @return 职称信息
     */
    ProfessionalTitleVo getProfessionalTitle(Long id);

    /**
     * 根据ID获取职称信息
     *
     * @param id
     * @return 职称信息
     */
    ProfessionalTitle getBaseProfessionalTitle(Long id);

    /**
     * 分页查询职称信息
     *
     * @param professionalTitleQueryDto 职称查询入参
     * @return 职称信息
     */
    QueryResultVo<ProfessionalTitleVo> queryProfessionalTitle(ProfessionalTitleQueryDto professionalTitleQueryDto);



}
