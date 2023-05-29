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
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("公告入参")
public class AnnouncementDto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 公告类型id
     */
    @ApiModelProperty(value = "公告类型id", required = true)
    @NotNull(message = "公告类型id不能为空")
    private Long announcementTypeId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @NotNull(message = "标题不能为空")
    @NotEmpty(message = "标题不能为空")
    @Size(max = 255, message = "标题不能超过255位")
    private String title;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sortNumber;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean isEnable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注不能超过255位")
    private String remarks;




}
