package com.xavier.pms.query;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "分页输入参数基类")
@Data
public class QueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Fields pageNo : 第几页
     */
    @Schema(description = "第几页")
    private int pageNo = 1;

    /**
     * @Fields pageSize : 每页多少数据
     */
    @Schema(description = "每页多少数据")
    private int pageSize = 10;

    /**
     * @Fields orderBy : 排序
     */
    @Schema(description = "排序")
    private String orderBy;

    /**
     * @Fields returnFields : 指定返回哪些字段
     */
    @Schema(description = "指定返回哪些字段")
    private String returnFields;

}
