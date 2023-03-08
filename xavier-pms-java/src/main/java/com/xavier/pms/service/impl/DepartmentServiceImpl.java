package com.xavier.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.convertor.DepartmentConvertor;
import com.xavier.pms.dto.DepartmentQueryDto;
import com.xavier.pms.model.Department;
import com.xavier.pms.dto.DepartmentDto;
import com.xavier.pms.vo.DepartmentVo;
import com.xavier.pms.dao.DepartmentMapper;
import com.xavier.pms.service.IDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;


/**
 * 业务逻辑层-部门实现类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
@Slf4j
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentConvertor.toDepartment(departmentDto);
        department.setId(null);
        super.save(department);
        return department.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateDepartment(DepartmentDto departmentDto) {
        getBaseDepartment(departmentDto.getId());
        Department department = DepartmentConvertor.toDepartment(departmentDto);
        return super.updateById(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteDepartment(Long id) {
        getBaseDepartment(id);
        // todo 判断是否有员工
        return super.removeById(id);
    }

    @Override
    public DepartmentVo getDepartment(Long id) {
        return DepartmentConvertor.toDepartmentVo(getBaseDepartment(id));
    }


    @Override
    public Department getBaseDepartment(Long id) {
        Department department = super.getById(id);
        if (Objects.isNull(department) || department.getIsDeleted()) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "部门");
        }
        return department;
    }

    @Override
    public QueryResultVo<DepartmentVo> queryDepartment(DepartmentQueryDto queryDTO) {
        Page<Department> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        LambdaQueryWrapper<Department> wrapper = Department.gw();
        if (Objects.nonNull(queryDTO.getId())) {
            // id不为空
            wrapper.eq(Department::getId, queryDTO.getId());
        }
        if (Objects.nonNull(queryDTO.getParentId())) {
            // 父id不为空
            wrapper.eq(Department::getParentId, queryDTO.getParentId());
        }
        if (StrUtil.isNotBlank(queryDTO.getDeptName())) {
            // 部门名称不为空
            wrapper.like(Department::getDeptName, queryDTO.getDeptName());
        }
        if (Objects.nonNull(queryDTO.getUserId())) {
            // 负责人id不为空
            wrapper.eq(Department::getUserId, queryDTO.getUserId());
        }
        if (StrUtil.isNotBlank(queryDTO.getRemarks())) {
            // 备注不为空
            wrapper.like(Department::getRemarks, queryDTO.getRemarks());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeFrom())) {
            // 创建时间大于等于
            wrapper.ge(Department::getCreateTime, queryDTO.getCreateTimeFrom());
        }
        if (Objects.nonNull(queryDTO.getCreateTimeTo())) {
            // 创建时间小于等于
            wrapper.le(Department::getCreateTime, queryDTO.getCreateTimeTo());
        }
        wrapper.orderByDesc(Department::getId);
        Page<Department> result = super.page(page, wrapper);
        QueryResultVo<DepartmentVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, DepartmentVo.class);
        queryResultVo.setRecords(DepartmentConvertor.toDepartmentVoList(result.getRecords()));
        return queryResultVo;
    }

}
