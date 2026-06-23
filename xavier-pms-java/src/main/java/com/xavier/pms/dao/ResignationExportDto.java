package com.xavier.pms.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 离职记录导出DTO
 *
 * @author Xavier
 */
@Data
public class ResignationExportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty("工号")
    private String employeeNumber;

    @ExcelProperty("姓名")
    private String nickName;

    @ExcelProperty("部门")
    private String departmentName;

    @ExcelProperty("离职类型")
    private String resignationTypeDesc;

    @ExcelProperty("离职日期")
    private String resignationDate;

    @ExcelProperty("离职原因")
    private String resignationReason;

    @ExcelProperty("交接人")
    private String handoverUserName;
}
