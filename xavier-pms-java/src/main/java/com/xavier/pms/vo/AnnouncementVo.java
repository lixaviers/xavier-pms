package com.xavier.pms.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "公告出参")
public class AnnouncementVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 公告类型id
     */
    @Schema(description = "公告类型id")
    private Long announcementTypeId;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 类型名称
     */
    @Schema(description = "类型名称")
    private String typeName;

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
    @Schema(description = "排序")
    private Integer sortNumber;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean isEnable;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
