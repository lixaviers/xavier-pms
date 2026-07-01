package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 汇报统计出参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "汇报统计出参")
public class ReportStatVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户姓名")
    private String nickName;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "日报提交数")
    private Integer dailyCount;

    @Schema(description = "周报提交数")
    private Integer weeklyCount;

    @Schema(description = "月报提交数")
    private Integer monthlyCount;

    @Schema(description = "总提交数")
    private Integer totalCount;
}
