package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "员工工作经历入参")
public class EmployeeWorkExperienceDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单位名称
     */
    @Schema(description = "单位名称", required = true)
    @NotNull(message = "单位名称不能为空")
    @NotEmpty(message = "单位名称不能为空")
    @Size(max = 50, message = "单位名称不能超过50位")
    private String company;

    /**
     * 部门
     */
    @Schema(description = "部门", required = true)
    @NotNull(message = "部门不能为空")
    @NotEmpty(message = "部门不能为空")
    @Size(max = 50, message = "部门不能超过50位")
    private String department;

    /**
     * 职位
     */
    @Schema(description = "职位", required = true)
    @NotNull(message = "职位不能为空")
    @NotEmpty(message = "职位不能为空")
    @Size(max = 50, message = "职位不能超过50位")
    private String position;

    /**
     * 入职日期
     */
    @Schema(description = "入职日期", required = true)
    @NotNull(message = "入职日期不能为空")
    private LocalDate entryDate;

    /**
     * 离职日期
     */
    @Schema(description = "离职日期", required = true)
    @NotNull(message = "离职日期不能为空")
    private LocalDate leaveOfficeDate;


}
