package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dao.UserMapper;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.EmployeeQueryDto;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Department;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IDepartmentService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.service.IUserTokenService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.utils.PasswordEncoderUtil;
import com.xavier.pms.vo.EmployeeCardVo;
import com.xavier.pms.vo.EmployeeListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
    @Resource
    @Lazy // 延迟，避免循环依赖报错
    private IDepartmentService departmentService;

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
    @Transactional(rollbackFor = Exception.class)
    public void createUser(EmployeeAddDto userDto) {
        User user = BeanUtil.beanCopy(userDto, User.class);
        user.setNickNamePy(PinyinUtil.getPinyin(userDto.getNickName(), ""));
        user.setWorkExperience(JSON.toJSONString(userDto.getWorkExperienceList()));
        user.setFamilyInformation(JSON.toJSONString(userDto.getFamilyInfoList()));
        user.setEmergencyContact(JSON.toJSONString(userDto.getEmergencyContactList()));
        user.setId(null);
        user.setIsAdmin(false);
        // 设置默认密码
        user.setUserPwd(passwordEncoderUtil.encode(Constant.DEFAULT_USER_PWD));
        user.setIsInitPwd(true);
        user.setUserStatus(UserStatusEnum.NORMAL.getValue());
        add(user);
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
    public User getBaseUser(Long id) {
        User user = super.getById(id);
        if (Objects.isNull(user)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        return user;
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

    @Override
    public EmployeeCardVo getCard(Long id) {
        return baseMapper.getCard(id);
    }

    @Override
    public User getDirectLeader(Long id) {
        User user = getBaseUser(id);
        if (Objects.isNull(user.getDirectLeaderId()) || Objects.equals(user.getDirectLeaderId(), 0L)) {
            return null;
        }
        return super.getById(user.getDirectLeaderId());
    }

    @Override
    public User getDepartmentUser(Long id) {
        User user = getBaseUser(id);
        Department department = departmentService.getBaseDepartment(user.getDepartmentId());
        if (Objects.isNull(department.getUserId()) || Objects.equals(department.getUserId(), 0L)) {
            return null;
        }
        return super.getById(department.getUserId());
    }
}
