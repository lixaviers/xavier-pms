package com.xavier.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("员工json出参")
public class EmployeeJsonVo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;


}
