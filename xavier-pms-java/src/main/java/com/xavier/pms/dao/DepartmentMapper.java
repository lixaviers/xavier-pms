package com.xavier.pms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xavier.pms.dto.DepartmentQueryDto;
import com.xavier.pms.model.Department;
import com.xavier.pms.vo.DepartmentVo;
import org.apache.ibatis.annotations.Param;

/**
 * 数据持久层-部门管理类
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    // 分页查询部门信息
    Page<DepartmentVo> queryDepartment(@Param("dto") DepartmentQueryDto queryDTO, Page<Department> page);

}
