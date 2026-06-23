package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "工作台数据出参")
public class DashboardVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "待我审批数量")
    private Long pendingAuditCount;

    @Schema(description = "我已处理数量")
    private Long dealtCount;

    @Schema(description = "我发起的审批中数量")
    private Long myProcessingCount;

    @Schema(description = "抄送我的未读数量")
    private Long ccUnreadCount;

    @Schema(description = "员工总数")
    private Long employeeCount;

    @Schema(description = "部门总数")
    private Long departmentCount;

    @Schema(description = "待办事项列表")
    private List<TodoItem> todoList;

    @Schema(description = "最近公告列表")
    private List<AnnouncementItem> announcementList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "待办事项项")
    public static class TodoItem implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "审批单id")
        private Long id;

        @Schema(description = "审批名称")
        private String approvalName;

        @Schema(description = "审批状态描述")
        private String auditStatusDesc;

        @Schema(description = "创建时间")
        private String createTime;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "公告项")
    public static class AnnouncementItem implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "id")
        private Long id;

        @Schema(description = "标题")
        private String title;

        @Schema(description = "类型名称")
        private String typeName;

        @Schema(description = "创建时间")
        private String createTime;

    }

}
