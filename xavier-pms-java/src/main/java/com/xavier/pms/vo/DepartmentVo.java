package com.xavier.pms.vo;

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
@ApiModel("部门出参")
public class DepartmentVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    private Long parentId;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
     * 负责人id
     */
    @ApiModelProperty("负责人id")
    private Long userId;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    private String nickName;

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
