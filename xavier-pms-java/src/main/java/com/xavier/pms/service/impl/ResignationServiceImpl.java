package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.ResignationMapper;
import com.xavier.pms.dto.ResignationAddDto;
import com.xavier.pms.dto.ResignationQueryDto;
import com.xavier.pms.enums.ResignationTypeEnum;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Resignation;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IResignationService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ResignationVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 业务逻辑层-离职记录实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("resignationService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ResignationServiceImpl extends ServiceImpl<ResignationMapper, Resignation> implements IResignationService {

    private final IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resign(ResignationAddDto dto) {
        // 校验员工是否存在
        User user = userService.getBaseUser(dto.getUserId());

        // 校验员工当前状态是否为正常
        if (!UserStatusEnum.NORMAL.equal(user.getUserStatus())) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "该员工不是正常状态，无法办理离职");
        }

        // 校验离职类型
        if (!ResignationTypeEnum.exists(dto.getResignationType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "离职类型");
        }

        // 校验交接人是否存在
        if (Objects.nonNull(dto.getHandoverUserId())) {
            userService.getBaseUser(dto.getHandoverUserId());
        }

        // 保存离职记录
        Resignation resignation = BeanUtil.beanCopy(dto, Resignation.class);
        resignation.setId(null);
        super.save(resignation);

        // 更新员工状态为已离职
        User updateUser = new User();
        updateUser.setId(dto.getUserId());
        updateUser.setUserStatus(UserStatusEnum.HAVE_LEFT.getValue());
        userService.updateById(updateUser);
    }

    @Override
    public QueryResultVo<ResignationVo> queryResignation(ResignationQueryDto dto) {
        Page<ResignationVo> page = new Page<>();
        page.setCurrent(dto.getPageNo());
        page.setSize(dto.getPageSize());
        Page<ResignationVo> result = baseMapper.queryResignation(dto, page);
        QueryResultVo<ResignationVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, ResignationVo.class);
        queryResultVo.setRecords(result.getRecords());
        return queryResultVo;
    }

    @Override
    public ResignationVo getResignationDetail(Long id) {
        ResignationVo vo = baseMapper.getResignationDetail(id);
        if (Objects.isNull(vo)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "离职记录");
        }
        return vo;
    }

    @Override
    public Resignation getBaseResignation(Long id) {
        Resignation resignation = super.getById(id);
        if (Objects.isNull(resignation)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "离职记录");
        }
        return resignation;
    }

}
