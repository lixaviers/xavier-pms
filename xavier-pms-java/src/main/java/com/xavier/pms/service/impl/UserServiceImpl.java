package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dao.UserMapper;
import com.xavier.pms.dto.ApprovalQueryDto;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.EmployeeQueryDto;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.service.IUserTokenService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.utils.PasswordEncoderUtil;
import com.xavier.pms.vo.ApprovalEmployeeVo;
import com.xavier.pms.vo.EmployeeListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 业务逻辑层-用户实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private PasswordEncoderUtil passwordEncoderUtil;

    @Resource
    private IUserTokenService userTokenService;

    @Override
    public String login(LoginDto dto) {
        // 根据用户名查找
        User user = getByEmployeeNumber(dto.getUserName());
        if (Objects.isNull(user)) {
            throw new ServiceException(ResultCode.C503);
        }
        // 匹配密码
        if (!passwordEncoderUtil.matches(dto.getUserPwd(), user.getUserPwd())) {
            throw new ServiceException(ResultCode.C503);
        }
        // 用户状态
        if (!UserStatusEnum.NORMAL.equal(user.getUserStatus())) {
            throw new ServiceException(ResultCode.C504);
        }

        // 生成token，基于数据库表
        return userTokenService.createUserToken(user.getId());
    }

    @Override
    public User getByToken(String token) {
        return baseMapper.getByToken(token);
    }

    /**
     * 根据工号查找
     *
     * @param employeeNumber 工号
     * @return
     */
    private User getByEmployeeNumber(String employeeNumber) {
        return super.getOne(User.gw().eq(User::getEmployeeNumber, employeeNumber));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUser(EmployeeAddDto userDto) {
        User user = BeanUtil.beanCopy(userDto, User.class);
        user.setFamilyInformation(JSON.toJSONString(userDto.getFamilyInfoList()));
        user.setEmergencyContact(JSON.toJSONString(userDto.getEmergencyContactList()));
        user.setId(null);
        user.setIsAdmin(false);
        // 设置默认密码
        user.setUserPwd(passwordEncoderUtil.encode(Constant.DEFAULT_USER_PWD));
        user.setIsInitPwd(true);
        // 待审批状态
        user.setUserStatus(UserStatusEnum.PENDING_APPROVAL.getValue());
        add(user);
        return user.getId();
    }

    private void add(User user) {
        try {
            String maxEmployeeNumber = baseMapper.getMaxEmployeeNumber();
            if (StrUtil.isEmpty(maxEmployeeNumber)) {
                maxEmployeeNumber = "10001";
            } else {
                maxEmployeeNumber = Integer.parseInt(maxEmployeeNumber) + 1 + "";
            }
            user.setEmployeeNumber(maxEmployeeNumber);
            super.save(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                add(user);
            }
            throw e;
        }
    }

    @Override
    public QueryResultVo<ApprovalEmployeeVo> queryApproval(ApprovalQueryDto dto) {
        Page<User> page = new Page<>();
        page.setCurrent(dto.getPageNo());
        page.setSize(dto.getPageSize());
        LambdaQueryWrapper<User> wrapper = User.gw();
        if (StrUtil.isNotBlank(dto.getEmployeeNumber())) {
            // 工号不为空
            wrapper.eq(User::getEmployeeNumber, dto.getEmployeeNumber());
        }
        if (StrUtil.isNotBlank(dto.getNickName())) {
            // 姓名不为空
            wrapper.like(User::getNickName, dto.getNickName());
        }
        if (StrUtil.isNotBlank(dto.getMobile())) {
            // 手机号不为空
            wrapper.like(User::getMobile, dto.getMobile());
        }
        wrapper.eq(User::getUserStatus, UserStatusEnum.PENDING_APPROVAL.getValue());
        wrapper.orderByDesc(User::getId);
        Page<User> result = super.page(page, wrapper);
        QueryResultVo<ApprovalEmployeeVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, ApprovalEmployeeVo.class);
        queryResultVo.setRecords(BeanUtil.beanCopy(result.getRecords(), ApprovalEmployeeVo.class));
        return queryResultVo;
    }

    @Override
    public User getBaseUser(Long id) {
        User user = super.getById(id);
        if (Objects.isNull(user) || user.getIsDeleted()) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void approval(List<Long> idList) {
        User bean = new User();
        bean.setUserStatus(UserStatusEnum.NORMAL.getValue());
        super.update(bean, User.gw().in(User::getId, idList));
    }

    @Override
    public QueryResultVo<EmployeeListVo> queryEmployee(EmployeeQueryDto dto) {
        Page<User> page = new Page<>();
        page.setCurrent(dto.getPageNo());
        page.setSize(dto.getPageSize());
        Page<EmployeeListVo> result = baseMapper.queryEmployee(dto, page);
        QueryResultVo<EmployeeListVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, EmployeeListVo.class);
        queryResultVo.setRecords(result.getRecords());
        return queryResultVo;
    }

    @Override
    public List<EmployeeListVo> getByDepartmentId(Long departmentId) {
        List<User> list = super.list(User.gw()
                .eq(User::getDepartmentId, departmentId)
                .eq(User::getUserStatus, UserStatusEnum.NORMAL.getValue())
        );
        return BeanUtil.beanCopy(list, EmployeeListVo.class);
    }

}
