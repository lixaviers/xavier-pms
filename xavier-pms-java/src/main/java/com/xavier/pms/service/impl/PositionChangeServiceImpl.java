package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.dao.PositionChangeMapper;
import com.xavier.pms.dto.PositionChangeAddDto;
import com.xavier.pms.dto.PositionChangeQueryDto;
import com.xavier.pms.enums.PositionChangeTypeEnum;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.PositionChange;
import com.xavier.pms.model.Post;
import com.xavier.pms.model.ProfessionalTitle;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IPositionChangeService;
import com.xavier.pms.service.IPostService;
import com.xavier.pms.service.IProfessionalTitleService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.PositionChangeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 业务逻辑层-员工职位/职称变动实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("positionChangeService")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class PositionChangeServiceImpl extends ServiceImpl<PositionChangeMapper, PositionChange> implements IPositionChangeService {

    private final IUserService userService;
    private final IPostService postService;
    private final IProfessionalTitleService professionalTitleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void change(PositionChangeAddDto dto) {
        // 校验员工是否存在
        User user = userService.getBaseUser(dto.getUserId());

        // 校验员工当前状态是否为正常
        if (!UserStatusEnum.NORMAL.equal(user.getUserStatus())) {
            throw new ServiceException(ResultCode.COMMON_MESSAGE, "该员工不是正常状态，无法办理变动");
        }

        // 校验变动类型
        if (!PositionChangeTypeEnum.exists(dto.getChangeType())) {
            throw new ServiceException(ResultCode.COMMON_PARAM_INVALID, "变动类型");
        }

        // 补充变动前信息 & 校验变动后信息 & 更新员工表
        if (PositionChangeTypeEnum.POST_CHANGE.equal(dto.getChangeType())) {
            // 职位变动
            dto.setBeforeId(user.getPostId());
            dto.setBeforeName(null);

            // 查询变动前职位名称
            if (Objects.nonNull(user.getPostId()) && user.getPostId() > 0) {
                Post beforePost = postService.getById(user.getPostId());
                if (Objects.nonNull(beforePost)) {
                    dto.setBeforeName(beforePost.getPostName());
                }
            }

            // 校验变动后职位
            Post afterPost = postService.getById(dto.getAfterId());
            if (Objects.isNull(afterPost)) {
                throw new ServiceException(ResultCode.COMMON_MESSAGE, "变动后职位不存在");
            }
            dto.setAfterName(afterPost.getPostName());

            // 更新员工职位
            User updateUser = new User();
            updateUser.setId(dto.getUserId());
            updateUser.setPostId(dto.getAfterId());
            userService.updateById(updateUser);

        } else if (PositionChangeTypeEnum.TITLE_CHANGE.equal(dto.getChangeType())) {
            // 职称变动
            dto.setBeforeId(user.getProfessionalTitleId());
            dto.setBeforeName(null);

            // 查询变动前职称名称
            if (Objects.nonNull(user.getProfessionalTitleId()) && user.getProfessionalTitleId() > 0) {
                ProfessionalTitle beforeTitle = professionalTitleService.getById(user.getProfessionalTitleId());
                if (Objects.nonNull(beforeTitle)) {
                    dto.setBeforeName(beforeTitle.getTitleName());
                }
            }

            // 校验变动后职称
            ProfessionalTitle afterTitle = professionalTitleService.getById(dto.getAfterId());
            if (Objects.isNull(afterTitle)) {
                throw new ServiceException(ResultCode.COMMON_MESSAGE, "变动后职称不存在");
            }
            dto.setAfterName(afterTitle.getTitleName());

            // 更新员工职称
            User updateUser = new User();
            updateUser.setId(dto.getUserId());
            updateUser.setProfessionalTitleId(dto.getAfterId());
            userService.updateById(updateUser);
        }

        // 保存变动记录
        PositionChange positionChange = BeanUtil.beanCopy(dto, PositionChange.class);
        positionChange.setId(null);
        super.save(positionChange);
    }

    @Override
    public QueryResultVo<PositionChangeVo> queryPositionChange(PositionChangeQueryDto dto) {
        Page<PositionChangeVo> page = new Page<>();
        page.setCurrent(dto.getPageNo());
        page.setSize(dto.getPageSize());
        Page<PositionChangeVo> result = baseMapper.queryPositionChange(dto, page);
        QueryResultVo<PositionChangeVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, PositionChangeVo.class);
        queryResultVo.setRecords(result.getRecords());
        return queryResultVo;
    }

    @Override
    public PositionChangeVo getPositionChangeDetail(Long id) {
        PositionChangeVo vo = baseMapper.getPositionChangeDetail(id);
        if (Objects.isNull(vo)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "变动记录");
        }
        return vo;
    }

    @Override
    public PositionChange getBasePositionChange(Long id) {
        PositionChange positionChange = super.getById(id);
        if (Objects.isNull(positionChange)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "变动记录");
        }
        return positionChange;
    }

}
