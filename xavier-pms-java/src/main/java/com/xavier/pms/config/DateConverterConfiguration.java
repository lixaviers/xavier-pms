package com.xavier.pms.config;

import org.springframework.boot.jackson.JacksonComponent;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Spring Boot 4 + Jackson 3 时间类型序列化/反序列化配置
 * 使用 @JacksonComponent 自动注册到 JsonMapper
 */
@JacksonComponent
public class DateConverterConfiguration {

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATETIME);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_TIME);

    /**
     * LocalDate 序列化
     */
    @JacksonComponent
    public static class LocalDateSerializer extends ValueSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
            if (value != null) {
                gen.writeString(DATE_FORMATTER.format(value));
            } else {
                gen.writeNull();
            }
        }
    }

    /**
     * LocalDate 反序列化
     */
    @JacksonComponent
    public static class LocalDateDeserializer extends ValueDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
            String text = p.getValueAsString();
            if (text == null || text.isEmpty()) {
                return null;
            }
            return LocalDate.parse(text, DATE_FORMATTER);
        }
    }

    /**
     * LocalDateTime 序列化
     */
    @JacksonComponent
    public static class LocalDateTimeSerializer extends ValueSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
            if (value != null) {
                gen.writeString(DATETIME_FORMATTER.format(value));
            } else {
                gen.writeNull();
            }
        }
    }

    /**
     * LocalDateTime 反序列化
     */
    @JacksonComponent
    public static class LocalDateTimeDeserializer extends ValueDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
            String text = p.getValueAsString();
            if (text == null || text.isEmpty()) {
                return null;
            }
            return LocalDateTime.parse(text, DATETIME_FORMATTER);
        }
    }

    /**
     * LocalTime 序列化
     */
    @JacksonComponent
    public static class LocalTimeSerializer extends ValueSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
            if (value != null) {
                gen.writeString(TIME_FORMATTER.format(value));
            } else {
                gen.writeNull();
            }
        }
    }

    /**
     * LocalTime 反序列化
     */
    @JacksonComponent
    public static class LocalTimeDeserializer extends ValueDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
            String text = p.getValueAsString();
            if (text == null || text.isEmpty()) {
                return null;
            }
            return LocalTime.parse(text, TIME_FORMATTER);
        }
    }

}
