package com.xavier.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("应用流程json出参")
public class ApplicationProcessItemJsonVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ApiModelProperty("类型 parent:直属上级 dept:部门负责人 role:角色 userGroup:员工组 employee:指定员工 submitter:提交人")
    private String type;

    /**
     * 类型描述
     */
    @ApiModelProperty("类型描述")
    private String typeDesc;

    /**
     * id列表
     */
    @ApiModelProperty("id列表")
    private List<Long> idList;

    /**
     * 员工列表
     */
    @ApiModelProperty("员工列表")
    private List<EmployeeJsonVo> employeeList;


}
