package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.ProfessionalTitleConvertor;
import com.xavier.pms.dao.ProfessionalTitleMapper;
import com.xavier.pms.dto.ProfessionalTitleDto;
import com.xavier.pms.dto.ProfessionalTitleQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.ProfessionalTitle;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IProfessionalTitleService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ProfessionalTitleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-职称实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("professionalTitleService")
public class ProfessionalTitleServiceImpl extends ServiceImpl<ProfessionalTitleMapper, ProfessionalTitle> implements IProfessionalTitleService {

    @Resource
    private IUserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteProfessionalTitle(List<Long> idList) {
        // 判断是否有员工
        if (userService.count(User.gw().in(User::getProfessionalTitleId, idList)) > 0) {
            throw new ServiceException("职称下有员工，无法删除");
        }
        return super.removeBatchByIds(idList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createProfessionalTitle(ProfessionalTitleDto professionalTitleDto) {
        ProfessionalTitle professionalTitle = ProfessionalTitleConvertor.toProfessionalTitle(professionalTitleDto);
        professionalTitle.setId(null);
        super.save(professionalTitle);
        return professionalTitle.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateProfessionalTitle(ProfessionalTitleDto professionalTitleDto) {
        getBaseProfessionalTitle(professionalTitleDto.getId());
        ProfessionalTitle professionalTitle = ProfessionalTitleConvertor.toProfessionalTitle(professionalTitleDto);
        return super.updateById(professionalTitle);
    }

    @Override
    public ProfessionalTitleVo getProfessionalTitle(Long id) {
        return ProfessionalTitleConvertor.toProfessionalTitleVo(getBaseProfessionalTitle(id));
    }


    @Override
    public ProfessionalTitle getBaseProfessionalTitle(Long id) {
        ProfessionalTitle professionalTitle = super.getById(id);
        if (Objects.isNull(professionalTitle)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "职称");
        }
        return professionalTitle;
    }

    @Override
    public QueryResultVo<ProfessionalTitleVo> queryProfessionalTitle(ProfessionalTitleQueryDto queryDTO) {
        Page<ProfessionalTitle> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<ProfessionalTitle> wrapper = ProfessionalTitle.gw();
        if (StrUtil.isNotBlank(queryDTO.getTitleName())) {
            // 职称不为空
            wrapper.like(ProfessionalTitle::getTitleName, queryDTO.getTitleName());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(ProfessionalTitle::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(ProfessionalTitle::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByAsc(ProfessionalTitle::getId);
        Page<ProfessionalTitle> result = super.page(page, wrapper);
        QueryResultVo<ProfessionalTitleVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, ProfessionalTitleVo.class);
        queryResultVo.setRecords(ProfessionalTitleConvertor.toProfessionalTitleVoList(result.getRecords()));
        return queryResultVo;
    }

}
