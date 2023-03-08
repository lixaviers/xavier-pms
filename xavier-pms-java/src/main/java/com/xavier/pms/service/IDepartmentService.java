package com.xavier.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xavier.pms.dto.DepartmentDto;
import com.xavier.pms.dto.DepartmentQueryDto;
import com.xavier.pms.vo.DepartmentVo;
import com.xavier.pms.model.Department;
import com.xavier.pms.query.QueryResultVo;

import java.util.List;

/**
 * 业务逻辑层-部门接口类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 创建部门
     *
     * @param departmentDto 部门入参
     * @return id
     */
    Long createDepartment(DepartmentDto departmentDto);

    /**
     * 更新部门
     *
     * @param departmentDto 部门入参
     * @return 是否成功
     */
    Boolean updateDepartment(DepartmentDto departmentDto);

    /**
     * 删除部门
     *
     * @param id
     * @return 是否成功
     */
    Boolean deleteDepartment(Long id);

    /**
     * 根据ID获取部门信息
     *
     * @param id
     * @return 部门信息
     */
    DepartmentVo getDepartment(Long id);

    /**
     * 根据ID获取部门信息
     *
     * @param id
     * @return 部门信息
     */
    Department getBaseDepartment(Long id);

    /**
     * 分页查询部门信息
     *
     * @param departmentQueryDto 部门查询入参
     * @return 部门信息
     */
    QueryResultVo<DepartmentVo> queryDepartment(DepartmentQueryDto departmentQueryDto);



}
