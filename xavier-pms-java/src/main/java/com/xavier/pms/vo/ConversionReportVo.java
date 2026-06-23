package com.xavier.pms.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 转正统计报表VO
 *
 * @author Xavier
 */
@Data
public class ConversionReportVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 试用期员工总数 */
    private Long probationCount;
    /** 已转正员工数 */
    private Long convertedCount;
    /** 转正率(%) */
    private Double conversionRate;
    /** 本月应转正人数 */
    private Long thisMonthConversionCount;

    /** 试用期分析（按月统计转正人数） */
    private List<MonthlyItem> monthlyConversion;
    /** 试用期时长分布 */
    private List<ProbationPeriodItem> probationPeriodDistribution;

    @Data
    public static class MonthlyItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 月份 yyyy-MM */
        private String month;
        /** 转正人数 */
        private Long count;
    }

    @Data
    public static class ProbationPeriodItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 试用期(月) */
        private Integer probationPeriod;
        /** 人数 */
        private Long count;
    }
}
