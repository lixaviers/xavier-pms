package com.xavier.pms.convertor;

import com.xavier.pms.model.Department;
import com.xavier.pms.dto.DepartmentDto;
import com.xavier.pms.vo.DepartmentVo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 部门对象相互转换
 *
 * @author Xavier
 * @version 1.0
 * @CopyRright (c): <素焉>
 */
public abstract class DepartmentConvertor {

    private static final BeanCopier beanCopierForDepartmentVo = BeanCopier.create(Department.class, DepartmentVo.class, false);
    private static final BeanCopier beanCopierForDepartment = BeanCopier.create(DepartmentDto.class, Department.class, false);

    public static DepartmentVo toDepartmentVo(Department department) {
        if (Objects.isNull(department)) {
            return null;
        }
        DepartmentVo departmentVo = new DepartmentVo();
        beanCopierForDepartmentVo.copy(department, departmentVo, null);
        return departmentVo;
    }

    public static Department toDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        beanCopierForDepartment.copy(departmentDto, department, null);
        return department;
    }

    public static List<DepartmentVo> toDepartmentVoList(List<Department> departmentList) {
        if (Objects.isNull(departmentList) || departmentList.isEmpty()) {
            return new ArrayList<>();
        }
        List<DepartmentVo> departmentInfoList = new ArrayList<DepartmentVo>(departmentList.size());
        for (Department department : departmentList) {
            departmentInfoList.add(toDepartmentVo(department));
        }
        return departmentInfoList;
    }

}
