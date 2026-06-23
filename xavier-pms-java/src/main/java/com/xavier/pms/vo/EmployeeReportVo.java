package com.xavier.pms.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 员工信息报表VO
 *
 * @author Xavier
 */
@Data
public class EmployeeReportVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 员工总数 */
    private Long totalCount;
    /** 正式员工数 */
    private Long normalCount;
    /** 待审批员工数 */
    private Long pendingCount;
    /** 已离职员工数 */
    private Long leftCount;

    /** 性别分布 */
    private List<DistributionItem> genderDistribution;
    /** 学历分布 */
    private List<DistributionItem> degreeDistribution;
    /** 政治面貌分布 */
    private List<DistributionItem> politicalDistribution;
    /** 婚姻状况分布 */
    private List<DistributionItem> maritalDistribution;
    /** 部门分布 */
    private List<DistributionItem> departmentDistribution;

    @Data
    public static class DistributionItem implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /** 名称 */
        private String name;
        /** 数量 */
        private Long count;

        public DistributionItem() {}

        public DistributionItem(String name, Long count) {
            this.name = name;
            this.count = count;
        }
    }
}
