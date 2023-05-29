package com.xavier.pms.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xavier.pms.utils.LongJsonDeserializer;
import com.xavier.pms.utils.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("公告出参")
public class AnnouncementVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty("id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 公告类型id
     */
    @ApiModelProperty("公告类型id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long announcementTypeId;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 类型名称
     */
    @ApiModelProperty("类型名称")
    private String typeName;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sortNumber;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用")
    private Boolean isEnable;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
