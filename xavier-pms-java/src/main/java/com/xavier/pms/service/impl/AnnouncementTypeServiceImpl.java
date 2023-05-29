package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.AnnouncementTypeConvertor;
import com.xavier.pms.dao.AnnouncementTypeMapper;
import com.xavier.pms.dto.AnnouncementTypeDto;
import com.xavier.pms.dto.AnnouncementTypeQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Announcement;
import com.xavier.pms.model.AnnouncementType;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IAnnouncementService;
import com.xavier.pms.service.IAnnouncementTypeService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.AnnouncementTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-公告类型实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("announcementTypeService")
public class AnnouncementTypeServiceImpl extends ServiceImpl<AnnouncementTypeMapper, AnnouncementType> implements IAnnouncementTypeService {

    @Resource
    private IAnnouncementService announcementService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createAnnouncementType(AnnouncementTypeDto announcementTypeDto) {
        AnnouncementType announcementType = AnnouncementTypeConvertor.toAnnouncementType(announcementTypeDto);
        announcementType.setId(null);
        super.save(announcementType);
        return announcementType.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateAnnouncementType(AnnouncementTypeDto announcementTypeDto) {
        getBaseAnnouncementType(announcementTypeDto.getId());
        AnnouncementType announcementType = AnnouncementTypeConvertor.toAnnouncementType(announcementTypeDto);
        return super.updateById(announcementType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteAnnouncementType(List<Long> idList) {
        if (announcementService.count(Announcement.gw().in(Announcement::getAnnouncementTypeId, idList)) > 0) {
            throw new ServiceException("类型下有公告无法删除");
        }
        return super.removeBatchByIds(idList);
    }

    @Override
    public AnnouncementTypeVo getAnnouncementType(Long id) {
        return AnnouncementTypeConvertor.toAnnouncementTypeVo(getBaseAnnouncementType(id));
    }


    @Override
    public AnnouncementType getBaseAnnouncementType(Long id) {
        AnnouncementType announcementType = super.getById(id);
        if (Objects.isNull(announcementType)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "公告类型");
        }
        return announcementType;
    }

    @Override
    public QueryResultVo<AnnouncementTypeVo> queryAnnouncementType(AnnouncementTypeQueryDto queryDTO) {
        Page<AnnouncementType> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<AnnouncementType> wrapper = AnnouncementType.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(AnnouncementType::getId, queryDTO.getId());
        }
        if (StrUtil.isNotBlank(queryDTO.getTypeName())) {
            // 类型名称不为空
            wrapper.like(AnnouncementType::getTypeName, queryDTO.getTypeName());
        }
        if (Objects.nonNull(queryDTO.getSortNumber())) {
            // 排序不为空
            wrapper.eq(AnnouncementType::getSortNumber, queryDTO.getSortNumber());
        }
        if (StrUtil.isNotBlank(queryDTO.getRemarks())) {
            // 备注不为空
            wrapper.like(AnnouncementType::getRemarks, queryDTO.getRemarks());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(AnnouncementType::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(AnnouncementType::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByAsc(AnnouncementType::getSortNumber);
        wrapper.orderByDesc(AnnouncementType::getId);
        Page<AnnouncementType> result = super.page(page, wrapper);
        QueryResultVo<AnnouncementTypeVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, AnnouncementTypeVo.class);
        queryResultVo.setRecords(AnnouncementTypeConvertor.toAnnouncementTypeVoList(result.getRecords()));
        return queryResultVo;
    }

}
