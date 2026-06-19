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
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "公告入参")
public class AnnouncementDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id", required = true)
    private Long id;

    /**
     * 公告类型id
     */
    @Schema(description = "公告类型id", required = true)
    @NotNull(message = "公告类型id不能为空")
    private Long announcementTypeId;

    /**
     * 标题
     */
    @Schema(description = "标题", required = true)
    @NotNull(message = "标题不能为空")
    @NotEmpty(message = "标题不能为空")
    @Size(max = 255, message = "标题不能超过255位")
    private String title;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    /**
     * 排序
     */
    @Schema(description = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sortNumber;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnable;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
