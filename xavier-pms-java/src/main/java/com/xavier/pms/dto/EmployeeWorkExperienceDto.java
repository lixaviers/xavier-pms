package com.xavier.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工工作经历入参")
public class EmployeeWorkExperienceDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称", required = true)
    @NotNull(message = "单位名称不能为空")
    @NotEmpty(message = "单位名称不能为空")
    @Size(max = 50, message = "单位名称不能超过50位")
    private String company;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门", required = true)
    @NotNull(message = "部门不能为空")
    @NotEmpty(message = "部门不能为空")
    @Size(max = 50, message = "部门不能超过50位")
    private String department;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位", required = true)
    @NotNull(message = "职位不能为空")
    @NotEmpty(message = "职位不能为空")
    @Size(max = 50, message = "职位不能超过50位")
    private String position;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期", required = true)
    @NotNull(message = "入职日期不能为空")
    private LocalDate entryDate;

    /**
     * 离职日期
     */
    @ApiModelProperty(value = "离职日期", required = true)
    @NotNull(message = "离职日期不能为空")
    private LocalDate leaveOfficeDate;


}
