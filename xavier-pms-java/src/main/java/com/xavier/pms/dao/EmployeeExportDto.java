package com.xavier.pms.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 员工信息导出DTO
 *
 * @author Xavier
 */
@Data
public class EmployeeExportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty("工号")
    private String employeeNumber;

    @ExcelProperty("姓名")
    private String nickName;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("部门")
    private String departmentName;

    @ExcelProperty("职位")
    private String postName;

    @ExcelProperty("职称")
    private String professionalTitleName;

    @ExcelProperty("状态")
    private String userStatusDesc;

    @ExcelProperty("入职日期")
    private String entryDate;

    @ExcelProperty("最高学历")
    private String highestDegree;

    @ExcelProperty("政治面貌")
    private String politicalStatus;

    @ExcelProperty("婚姻状况")
    private String maritalStatus;
}
