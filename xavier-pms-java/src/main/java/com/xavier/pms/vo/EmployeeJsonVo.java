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
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;


}
