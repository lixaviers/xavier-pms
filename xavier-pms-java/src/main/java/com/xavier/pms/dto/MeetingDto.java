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


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "会议室入参")
public class MeetingDto implements Serializable {

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
     * 会议室名称
     */
    @Schema(description = "会议室名称", required = true)
    @NotNull(message = "会议室名称不能为空")
    @NotEmpty(message = "会议室名称不能为空")
    @Size(max = 255, message = "会议室名称不能超过255位")
    private String meetingName;

    /**
     * 位置
     */
    @Schema(description = "位置")
    @Size(max = 100, message = "位置不能超过100位")
    private String position;

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
