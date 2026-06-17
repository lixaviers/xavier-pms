package com.xavier.pms.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import java.io.IOException;

/**
 * Long 序列化规则
 */
@JacksonStdImpl
public class NumberSerializer extends com.fasterxml.jackson.databind.ser.std.NumberSerializer {

    public static final NumberSerializer INSTANCE = new NumberSerializer(Number.class);

    public NumberSerializer(Class<? extends Number> rawType) {
        super(rawType);
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toString());
    }
}
