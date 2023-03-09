package com.xavier.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.convertor.UserConvertor;
import com.xavier.pms.dao.UserMapper;
import com.xavier.pms.dto.EmployeeAddDto;
import com.xavier.pms.dto.LoginDto;
import com.xavier.pms.dto.UserQueryDto;
import com.xavier.pms.enums.UserStatusEnum;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.service.IUserTokenService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.utils.PasswordEncoderUtil;
import com.xavier.pms.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public UserVo getUser(Long id) {
        return UserConvertor.toUserVo(getBaseUser(id));
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
    public QueryResultVo<UserVo> queryUser(UserQueryDto queryDTO) {
        Page<User> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<User> wrapper = User.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(User::getId, queryDTO.getId());
        }
        if (StrUtil.isNotBlank(queryDTO.getEmployeeNumber())) {
            // 工号不为空
            wrapper.eq(User::getEmployeeNumber, queryDTO.getEmployeeNumber());
        }
        if (StrUtil.isNotBlank(queryDTO.getNickName())) {
            // 姓名不为空
            wrapper.like(User::getNickName, queryDTO.getNickName());
        }
        if (StrUtil.isNotBlank(queryDTO.getUserPwd())) {
            // 密码不为空
            wrapper.like(User::getUserPwd, queryDTO.getUserPwd());
        }
        if (StrUtil.isNotBlank(queryDTO.getMobile())) {
            // 手机号不为空
            wrapper.like(User::getMobile, queryDTO.getMobile());
        }
        if (StrUtil.isNotBlank(queryDTO.getEmail())) {
            // 邮箱不为空
            wrapper.like(User::getEmail, queryDTO.getEmail());
        }
        if (Objects.nonNull(queryDTO.getDepartmentId())) {
            // 部门id不为空
            wrapper.eq(User::getDepartmentId, queryDTO.getDepartmentId());
        }
        if (Objects.nonNull(queryDTO.getPostId())) {
            // 职位id不为空
            wrapper.eq(User::getPostId, queryDTO.getPostId());
        }
        if (Objects.nonNull(queryDTO.getProfessionalTitleId())) {
            // 职称id不为空
            wrapper.eq(User::getProfessionalTitleId, queryDTO.getProfessionalTitleId());
        }
        if (StrUtil.isNotBlank(queryDTO.getDocumentType())) {
            // 证件类型不为空
            wrapper.like(User::getDocumentType, queryDTO.getDocumentType());
        }
        if (StrUtil.isNotBlank(queryDTO.getDocumentNumber())) {
            // 证件号不为空
            wrapper.like(User::getDocumentNumber, queryDTO.getDocumentNumber());
        }
        if (Objects.nonNull(queryDTO.getBirthDateFrom())) {
            // 出生日期大于等于
            wrapper.ge(User::getBirthDate, queryDTO.getBirthDateFrom());
        }
        if (Objects.nonNull(queryDTO.getBirthDateTo())) {
            // 出生日期小于等于
            wrapper.le(User::getBirthDate, queryDTO.getBirthDateTo());
        }
        if (StrUtil.isNotBlank(queryDTO.getHouseholdRegistrationType())) {
            // 户籍类型不为空
            wrapper.like(User::getHouseholdRegistrationType, queryDTO.getHouseholdRegistrationType());
        }
        if (StrUtil.isNotBlank(queryDTO.getDomicileAddress())) {
            // 户籍地址不为空
            wrapper.like(User::getDomicileAddress, queryDTO.getDomicileAddress());
        }
        if (StrUtil.isNotBlank(queryDTO.getGender())) {
            // 性别不为空
            wrapper.like(User::getGender, queryDTO.getGender());
        }
        if (StrUtil.isNotBlank(queryDTO.getNationality())) {
            // 民族不为空
            wrapper.like(User::getNationality, queryDTO.getNationality());
        }
        if (StrUtil.isNotBlank(queryDTO.getMaritalStatus())) {
            // 婚姻状况不为空
            wrapper.like(User::getMaritalStatus, queryDTO.getMaritalStatus());
        }
        if (StrUtil.isNotBlank(queryDTO.getPoliticalStatus())) {
            // 政治面貌不为空
            wrapper.like(User::getPoliticalStatus, queryDTO.getPoliticalStatus());
        }
        if (StrUtil.isNotBlank(queryDTO.getSocialSecurityAccountNumber())) {
            // 社保账号不为空
            wrapper.like(User::getSocialSecurityAccountNumber, queryDTO.getSocialSecurityAccountNumber());
        }
        if (StrUtil.isNotBlank(queryDTO.getProvidentFundAccount())) {
            // 公积金账号不为空
            wrapper.like(User::getProvidentFundAccount, queryDTO.getProvidentFundAccount());
        }
        if (StrUtil.isNotBlank(queryDTO.getResidentialAddress())) {
            // 居住地址不为空
            wrapper.like(User::getResidentialAddress, queryDTO.getResidentialAddress());
        }
        if (StrUtil.isNotBlank(queryDTO.getHighestDegree())) {
            // 最高学历不为空
            wrapper.like(User::getHighestDegree, queryDTO.getHighestDegree());
        }
        if (StrUtil.isNotBlank(queryDTO.getGraduateSchool())) {
            // 毕业院校不为空
            wrapper.like(User::getGraduateSchool, queryDTO.getGraduateSchool());
        }
        if (Objects.nonNull(queryDTO.getGraduationTimeFrom())) {
            // 毕业时间大于等于
            wrapper.ge(User::getGraduationTime, queryDTO.getGraduationTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getGraduationTimeTo())) {
            // 毕业时间小于等于
            wrapper.le(User::getGraduationTime, queryDTO.getGraduationTimeTo());
        }
        if (StrUtil.isNotBlank(queryDTO.getMajor())) {
            // 所学专业不为空
            wrapper.like(User::getMajor, queryDTO.getMajor());
        }
        if (StrUtil.isNotBlank(queryDTO.getDepositBank())) {
            // 开户银行不为空
            wrapper.like(User::getDepositBank, queryDTO.getDepositBank());
        }
        if (StrUtil.isNotBlank(queryDTO.getBankCardNumber())) {
            // 银行卡号不为空
            wrapper.like(User::getBankCardNumber, queryDTO.getBankCardNumber());
        }
        if (Objects.nonNull(queryDTO.getIsAdmin())) {
            // 是否管理员不为空
            wrapper.eq(User::getIsAdmin, queryDTO.getIsAdmin());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(User::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(User::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(User::getId);
        Page<User> result = super.page(page, wrapper);
        QueryResultVo<UserVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, UserVo.class);
        queryResultVo.setRecords(UserConvertor.toUserVoList(result.getRecords()));
        return queryResultVo;
    }

}
