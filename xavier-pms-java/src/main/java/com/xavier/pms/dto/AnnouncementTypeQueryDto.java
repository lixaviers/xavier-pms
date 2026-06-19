package com.xavier.pms.dto;

import com.xavier.pms.query.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "公告类型查询入参")
public class AnnouncementTypeQueryDto extends QueryDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "类型名称")
    private String typeName;

    @Schema(description = "排序")
    private Integer sortNumber;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "创建时间起")
    private LocalDateTime createTimeFrom;

    @Schema(description = "创建时间止")
    private LocalDateTime createTimeTo;


}
