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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("审批单出参")
public class AuditFormVo implements Serializable {

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
     * 用户id
     */
    @ApiModelProperty("用户id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String nickName;

    /**
     * 审批id
     */
    @ApiModelProperty("审批id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long approvalId;

    /**
     * 审批名称
     */
    @ApiModelProperty("审批名称")
    private String approvalName;

    /**
     * 表单json
     */
    @ApiModelProperty("表单json")
    private String form;

    /**
     * 表单数据
     */
    @ApiModelProperty("表单数据")
    private String formData;

    /**
     * 审批流程
     */
    @ApiModelProperty("审批流程")
    private String approvalProcess;

    /**
     * 审批状态
     */
    @ApiModelProperty("审批状态")
    private Byte auditStatus;

    /**
     * 审批状态描述
     */
    @ApiModelProperty("审批状态描述")
    private String auditStatusDesc;

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

    /**
     * 展示操作按钮
     */
    @ApiModelProperty("展示操作按钮")
    private Button button;

    @Data
    @ToString(callSuper = true)
    @ApiModel("审批单按钮出参")
    public static class Button {

        @ApiModelProperty("是否展示审批按钮")
        private Boolean audit;

        @ApiModelProperty("是否展示办理按钮")
        private Boolean handler;

        @ApiModelProperty("是否展示撤回按钮")
        private Boolean revocation;

        public Button() {
            this.audit = false;
            this.handler = false;
            this.revocation = false;
        }
    }

}
