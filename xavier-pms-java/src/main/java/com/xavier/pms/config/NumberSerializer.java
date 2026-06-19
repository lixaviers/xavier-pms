package com.xavier.pms.config;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

/**
 * Long 序列化规则
 * 超出 JavaScript 安全整数范围的 Long 值序列化为字符串，防止前端精度丢失
 */
public class NumberSerializer extends ValueSerializer<Number> {

    private static final long MAX_SAFE_INTEGER = 9007199254740991L;
    private static final long MIN_SAFE_INTEGER = -9007199254740991L;

    public static final NumberSerializer INSTANCE = new NumberSerializer();

    public NumberSerializer() {
        super();
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        long longValue = value.longValue();
        // 超出 JS 安全整数范围，序列化为字符串
        if (longValue > MIN_SAFE_INTEGER && longValue < MAX_SAFE_INTEGER) {
            gen.writeNumber(value.toString());
        } else {
            gen.writeString(value.toString());
        }
    }

    /**
     * 将 NumberSerializer 注册到 JsonMapper
     */
    public static void registerTo(JsonMapper.Builder builder) {
        SimpleModule module = new SimpleModule("NumberSerializerModule");
        module.addSerializer(Number.class, INSTANCE);
        module.addSerializer(Long.class, INSTANCE);
        builder.addModule(module);
    }

}
