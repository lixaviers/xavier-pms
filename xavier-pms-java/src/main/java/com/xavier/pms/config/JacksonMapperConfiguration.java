package com.xavier.pms.config;

import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import tools.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson 3 全局配置
 * 配置 JsonMapper（ObjectMapper 的不可变替代）
 */
@Configuration
@ConditionalOnClass(JsonMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonMapperConfiguration {

    @Bean
    @Primary
    public JsonMapper jsonMapper() {
        JsonMapper.Builder builder = JsonMapper.builder();

        // 设置中国时区和语言
        builder.defaultLocale(Locale.CHINA);
        builder.defaultTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 注册 Long 序列化器（防止前端精度丢失）
        NumberSerializer.registerTo(builder);

        return builder.build();
    }

}
