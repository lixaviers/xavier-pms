package com.xavier.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.pms.convertor.DepartmentConvertor;
import com.xavier.pms.dao.DepartmentMapper;
import com.xavier.pms.dto.DepartmentDto;
import com.xavier.pms.dto.DepartmentQueryDto;
import com.xavier.pms.exception.ServiceException;
import com.xavier.pms.model.Department;
import com.xavier.pms.model.User;
import com.xavier.pms.query.QueryResultVo;
import com.xavier.pms.result.ResultCode;
import com.xavier.pms.service.IDepartmentService;
import com.xavier.pms.service.IUserService;
import com.xavier.pms.utils.BeanUtil;
import com.xavier.pms.vo.DepartmentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;


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

    @Resource
    private IUserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentConvertor.toDepartment(departmentDto);
        String startId = "0-";
        if (!Objects.equals(department.getParentId(), 0L)) {
            Department parent = getBaseDepartment(department.getParentId());
            startId = parent.getFullId() + "-";
        }
        department.setId(null);
        super.save(department);

        Department bean = new Department();
        bean.setId(department.getId());
        bean.setFullId(startId + department.getId());
        super.updateById(bean);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean updateDepartment(DepartmentDto departmentDto) {
        Department departmentLast = getBaseDepartment(departmentDto.getId());
        Department department = DepartmentConvertor.toDepartment(departmentDto);
        if (!Objects.equals(department.getParentId(), departmentLast.getParentId())) {
            String fullId = "0";
            if (!Objects.equals(department.getParentId(), 0L)) {
                Department parent = getBaseDepartment(department.getParentId());
                fullId = parent.getFullId();
            }
            department.setFullId(fullId + "-" + department.getId());
        }
        return super.updateById(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean deleteDepartment(Long id) {
        getBaseDepartment(id);
        // 判断是否有员工
        if (userService.count(User.gw().eq(User::getDepartmentId, id)) > 0) {
            throw new ServiceException("部门下有员工，无法删除");
        }
        return super.removeById(id);
    }

    @Override
    public DepartmentVo getDepartment(Long id) {
        return DepartmentConvertor.toDepartmentVo(getBaseDepartment(id));
    }


    @Override
    public Department getBaseDepartment(Long id) {
        Department department = super.getById(id);
        if (Objects.isNull(department)) {
            throw new ServiceException(ResultCode.DATA_NOT_EXIST, "部门");
        }
        return department;
    }

    @Override
    public QueryResultVo<DepartmentVo> queryDepartment(DepartmentQueryDto queryDTO) {
        Page<Department> page = new Page<>();
        page.setCurrent(queryDTO.getPageNo());
        page.setSize(queryDTO.getPageSize());
        Page<DepartmentVo> result = baseMapper.queryDepartment(queryDTO, page);
        QueryResultVo<DepartmentVo> queryResultVo = BeanUtil.pageToQueryResultVo(result, DepartmentVo.class);
        queryResultVo.setRecords(result.getRecords());
        return queryResultVo;
    }

}
