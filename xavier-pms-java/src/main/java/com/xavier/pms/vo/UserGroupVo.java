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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工组出参")
public class UserGroupVo implements Serializable {

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
     * 组名称
     */
    @ApiModelProperty("组名称")
    private String groupName;

    /**
     * 员工列表
     */
    @ApiModelProperty(value = "员工列表")
    private List<EmployeeJsonVo> employeeList;

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
