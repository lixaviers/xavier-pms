package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.constants.Constant;
import com.xavier.pms.dao.AuditFormFlowDetailMapper;
import com.xavier.pms.enums.AuditStatusEnum;
import com.xavier.pms.model.AuditFormFlow;
import com.xavier.pms.model.AuditFormFlowDetail;
import com.xavier.pms.model.User;
import com.xavier.pms.model.UserGroup;
import com.xavier.pms.service.IAuditFormFlowDetailService;
import com.xavier.pms.service.IUserGroupService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.ApprovalProcessItemJsonVo;
import com.xavier.pms.vo.ApprovalProcessJsonVo;
import com.xavier.pms.vo.EmployeeJsonVo;
import com.xavier.pms.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 业务逻辑层-审批单流程详细实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("auditFormFlowDetailService")
public class AuditFormFlowDetailServiceImpl extends ServiceImpl<AuditFormFlowDetailMapper, AuditFormFlowDetail> implements IAuditFormFlowDetailService {

    @Resource
    private IUserService userService;
    @Resource
    private IUserGroupService userGroupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuditFlowDetail(UserInfoVo loginUser, AuditFormFlow flow, ApprovalProcessJsonVo vo) {
        List<AuditFormFlowDetail> detailList = new ArrayList<>();
        if (Objects.equals(vo.getApprovalType(), Constant.APPROVAL_TYPE_APPROVAL) || Objects.equals(vo.getApprovalType(), Constant.APPROVAL_TYPE_HANDLER)) {
            if (Objects.equals(vo.getApprovalType(), Constant.APPROVAL_TYPE_APPROVAL) && !Objects.equals("manual", vo.getApprovalMode())) {
                // 非人工审批
                return;
            }
            // 人工审批/办理
            List<EmployeeJsonVo> employeeJsonVoList = getEmployeeListByProcess(loginUser, vo.getApprovalType(), vo.getEmptyType(), vo.getItemList(), vo.getEmployeeList());
            for (int i = 0; i < employeeJsonVoList.size(); i++) {
                EmployeeJsonVo employeeJsonVo = employeeJsonVoList.get(i);
                AuditFormFlowDetail auditFlowDetail = new AuditFormFlowDetail();
                auditFlowDetail.setAuditFormId(flow.getAuditFormId());
                auditFlowDetail.setAuditFormFlowId(flow.getId());
                auditFlowDetail.setUserId(employeeJsonVo.getId());
                auditFlowDetail.setNickName(employeeJsonVo.getNickName());
                auditFlowDetail.setApprovalType(vo.getApprovalType());
                if (Objects.equals(vo.getApprovalMoreType(), "order")) {
                    // 按顺序依次审批/办理
                    auditFlowDetail.setSortNumber(i + 1);
                } else {
                    auditFlowDetail.setSortNumber(1);
                }
                auditFlowDetail.setAuditStatus(AuditStatusEnum.PENDING.getValue());
                detailList.add(auditFlowDetail);
            }
        }
        if (Objects.equals(vo.getApprovalType(), Constant.APPROVAL_TYPE_START)) {
            // 提交
            AuditFormFlowDetail auditFlowDetail = new AuditFormFlowDetail();
            auditFlowDetail.setAuditFormId(flow.getAuditFormId());
            auditFlowDetail.setAuditFormFlowId(flow.getId());
            auditFlowDetail.setUserId(loginUser.getId());
            auditFlowDetail.setNickName(loginUser.getNickName());
            auditFlowDetail.setApprovalType(Constant.APPROVAL_TYPE_START);
            auditFlowDetail.setSortNumber(1);
            auditFlowDetail.setAuditStatus(AuditStatusEnum.PENDING.getValue());
            detailList.add(auditFlowDetail);
        }
        List<EmployeeJsonVo> ccList = getEmployeeListByProcess(loginUser, Constant.APPROVAL_TYPE_CC, "", vo.getCcList(), null);
        if (CollUtil.isNotEmpty(ccList)) {
            for (EmployeeJsonVo employeeJsonVo : ccList) {
                AuditFormFlowDetail auditFlowDetail = new AuditFormFlowDetail();
                auditFlowDetail.setAuditFormId(flow.getAuditFormId());
                auditFlowDetail.setAuditFormFlowId(flow.getId());
                auditFlowDetail.setUserId(employeeJsonVo.getId());
                auditFlowDetail.setNickName(employeeJsonVo.getNickName());
                auditFlowDetail.setApprovalType(Constant.APPROVAL_TYPE_CC);
                auditFlowDetail.setSortNumber(1);
                auditFlowDetail.setAuditStatus(AuditStatusEnum.PENDING.getValue());
                detailList.add(auditFlowDetail);
            }
        }
        if (CollUtil.isNotEmpty(detailList)) {
            super.saveBatch(detailList);
        }
    }

    /**
     * 根据流程获取员工列表
     *
     * @param loginUser    登录用户
     * @param approvalType 审批类型
     * @param emptyType    审批人为空时类型 autoPass:自动通过 employee:指定人员审批
     * @param itemList     审批流程项
     * @param employeeList 审批人为空时指定审批人员
     * @return
     */
    private List<EmployeeJsonVo> getEmployeeListByProcess(UserInfoVo loginUser, String approvalType, String emptyType, List<ApprovalProcessItemJsonVo> itemList, List<EmployeeJsonVo> employeeList) {
        List<EmployeeJsonVo> voList = new ArrayList<>();
        if (CollUtil.isNotEmpty(itemList)) {
            for (ApprovalProcessItemJsonVo vo : itemList) {
                switch (vo.getType()) {
                    case "parent":
                        // 直属上级
                        User user = userService.getDirectLeader(loginUser.getId());
                        if (Objects.nonNull(user)) {
                            voList.add(BeanUtil.beanCopy(user, EmployeeJsonVo.class));
                        }
                        break;
                    case "dept":
                        // 部门负责人
                        user = userService.getDepartmentUser(loginUser.getId());
                        if (Objects.nonNull(user)) {
                            voList.add(BeanUtil.beanCopy(user, EmployeeJsonVo.class));
                        }
                        break;
                    case "userGroup":
                        // 员工组
                        if (CollUtil.isNotEmpty(vo.getIdList())) {
                            List<UserGroup> userGroups = userGroupService.listByIds(vo.getIdList());
                            if (CollUtil.isNotEmpty(userGroups)) {
                                for (UserGroup userGroup : userGroups) {
                                    List<EmployeeJsonVo> jsonVoList = JSON.parseArray(userGroup.getMemberJson(), EmployeeJsonVo.class);
                                    if (CollUtil.isNotEmpty(jsonVoList)) {
                                        voList.addAll(jsonVoList);
                                    }
                                }
                            }
                        }
                        break;
                    case "employee":
                        // 指定员工
                        if (CollUtil.isNotEmpty(vo.getEmployeeList())) {
                            voList.addAll(vo.getEmployeeList());
                        }
                        break;
                    case "submitter":
                        // 提交人
                        voList.add(new EmployeeJsonVo(loginUser.getId(), loginUser.getNickName()));
                        break;
                }
            }
        }
        if (CollUtil.isEmpty(voList) && Objects.equals(approvalType, Constant.APPROVAL_TYPE_APPROVAL) && Objects.equals(emptyType, "employee") && CollUtil.isNotEmpty(employeeList)) {
            // 审批/办理人为空时，指定人员审批/办理
            voList = employeeList;
        }
        // 去除重复用户
        if (CollUtil.isNotEmpty(voList)) {
            voList = voList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(EmployeeJsonVo::getId))), ArrayList::new));
        }
        return voList;
    }

}
