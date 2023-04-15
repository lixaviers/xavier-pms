package com.xavier.pms.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName("tb_temp_data")
public class TempData {

    public static final LambdaQueryWrapper<TempData> gw() {
        return new LambdaQueryWrapper<>();
    }


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String a;
    private BigDecimal b;
    private BigDecimal c;
    private BigDecimal d;
    private String e;
    private String f;
    private BigDecimal g;
    private String h;
    private String i;
    private BigDecimal j;


}
