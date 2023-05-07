package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批单查询入参")
public class AuditFormQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty("类型 self:我发起的 deal:我处理的 cc:抄送我的")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "审批类型列表", hidden = true)
    private List<String> approvalTypeList;

    @ApiModelProperty("审批状态")
    private Byte auditStatus;

    @ApiModelProperty(value = "审批状态列表", hidden = true)
    private List<Byte> auditStatusList;

    @ApiModelProperty("创建时间起")
    private LocalDateTime createTimeFrom;

    @ApiModelProperty("创建时间止")
    private LocalDateTime createTimeTo;


}
