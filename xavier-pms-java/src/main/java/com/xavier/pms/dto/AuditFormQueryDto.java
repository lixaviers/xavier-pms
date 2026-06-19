package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "审批单查询入参")
public class AuditFormQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id", hidden = true)
    private Long userId;

    @Schema(description = "类型 self:我发起的 deal:我处理的 cc:抄送我的")
    @NotNull(message = "类型不能为空")
    private String type;

    @Schema(description = "审批类型列表", hidden = true)
    private List<String> approvalTypeList;

    @Schema(description = "审批状态")
    private Byte auditStatus;

    @Schema(description = "审批状态列表", hidden = true)
    private List<Byte> auditStatusList;

    @Schema(description = "创建时间起")
    private LocalDateTime createTimeFrom;

    @Schema(description = "创建时间止")
    private LocalDateTime createTimeTo;


}
