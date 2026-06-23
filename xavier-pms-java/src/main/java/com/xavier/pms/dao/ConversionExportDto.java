package com.xavier.pms.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 转正记录导出DTO
 *
 * @author Xavier
 */
@Data
public class ConversionExportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty("工号")
    private String employeeNumber;

    @ExcelProperty("姓名")
    private String nickName;

    @ExcelProperty("部门")
    private String departmentName;

    @ExcelProperty("职位")
    private String postName;

    @ExcelProperty("入职日期")
    private String entryDate;

    @ExcelProperty("试用期(月)")
    private Integer probationPeriod;

    @ExcelProperty("预计转正日期")
    private String estimatedConversionDate;

    @ExcelProperty("转正状态")
    private String conversionStatus;
}
