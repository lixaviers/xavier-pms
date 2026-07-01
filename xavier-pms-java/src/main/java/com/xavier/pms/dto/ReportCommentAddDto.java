package com.xavier.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * 汇报评论新增入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "汇报评论新增入参")
public class ReportCommentAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "汇报id", required = true)
    @NotNull(message = "汇报id不能为空")
    private Long reportId;

    @Schema(description = "评论内容", required = true)
    @NotBlank(message = "评论内容不能为空")
    @Size(max = 1000, message = "评论内容不能超过1000位")
    private String content;
}
