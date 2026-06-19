package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_operate_log")
public class OperateLog {

    public static final LambdaQueryWrapper<OperateLog> gw() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ip
     */
    @TableField(value = "user_ip")
    private String userIp;

    /**
     * 操作名
     */
    @TableField(value = "operate_name")
    private String operateName;

    /**
     * 请求方法名
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 请求地址
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 浏览器UserAgent
     */
    @TableField(value = "user_agent")
    private String userAgent;

    /**
     * Java方法名
     */
    @TableField(value = "java_method")
    private String javaMethod;

    /**
     * Java方法的参数
     */
    @TableField(value = "java_method_args")
    private String javaMethodArgs;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 链路追踪id
     */
    @TableField(value = "trace_id")
    private String traceId;

    /**
     * 登录凭证
     */
    @TableField(value = "token")
    private String token;

    /**
     * 执行时长，单位：毫秒
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 结果码
     */
    @TableField(value = "result_code")
    private Integer resultCode;

    /**
     * 结果提示
     */
    @TableField(value = "result_msg")
    private String resultMsg;

    /**
     * 结果数据
     */
    @TableField(value = "result_data")
    private String resultData;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;


}
