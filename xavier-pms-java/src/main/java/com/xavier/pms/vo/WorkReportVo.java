package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作汇报出参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "工作汇报出参")
public class WorkReportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "汇报标题")
    private String title;

    @Schema(description = "汇报类型: 1-日报 2-周报 3-月报")
    private Byte reportType;

    @Schema(description = "汇报类型描述")
    private String reportTypeDesc;

    @Schema(description = "使用的模板id")
    private Long templateId;

    @Schema(description = "模板名称")
    private String templateName;

    @Schema(description = "汇报人id")
    private Long userId;

    @Schema(description = "汇报人姓名")
    private String nickName;

    @Schema(description = "汇报人部门")
    private String deptName;

    @Schema(description = "汇报日期")
    private LocalDate reportDate;

    @Schema(description = "第几周")
    private Integer weekNumber;

    @Schema(description = "第几月")
    private Integer monthNumber;

    @Schema(description = "汇报内容（JSON格式）")
    private String content;

    @Schema(description = "工作总结")
    private String workSummary;

    @Schema(description = "工作计划")
    private String workPlan;

    @Schema(description = "问题与建议")
    private String issues;

    @Schema(description = "状态: 1-草稿 2-已提交")
    private Byte status;

    @Schema(description = "状态描述")
    private String statusDesc;

    @Schema(description = "提交时间")
    private LocalDateTime submitTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "评论列表")
    private List<CommentItem> comments;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "评论项")
    public static class CommentItem implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "id")
        private Long id;

        @Schema(description = "评论人id")
        private Long userId;

        @Schema(description = "评论人姓名")
        private String nickName;

        @Schema(description = "评论内容")
        private String content;

        @Schema(description = "评论时间")
        private LocalDateTime createTime;
    }
}
