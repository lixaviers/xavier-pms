package com.xavier.pms.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 离职统计报表VO
 *
 * @author Xavier
 */
@Data
public class ResignationReportVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 离职总人数 */
    private Long totalCount;
    /** 离职率(%) */
    private Double resignationRate;
    /** 本月离职人数 */
    private Long thisMonthCount;

    /** 离职原因分布 */
    private List<ReasonItem> reasonDistribution;
    /** 部门离职对比 */
    private List<DepartmentResignationItem> departmentResignation;
    /** 月度离职趋势 */
    private List<MonthlyItem> monthlyTrend;

    @Data
    public static class ReasonItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 离职类型 */
        private String typeName;
        /** 人数 */
        private Long count;
    }

    @Data
    public static class DepartmentResignationItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 部门名称 */
        private String departmentName;
        /** 部门总人数 */
        private Long totalCount;
        /** 离职人数 */
        private Long resignationCount;
    }

    @Data
    public static class MonthlyItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 月份 yyyy-MM */
        private String month;
        /** 离职人数 */
        private Long count;
    }
}
