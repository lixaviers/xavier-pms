package com.xavier.pms.utils;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;

public class ListLongJsonSerializer extends JsonSerializer<List<Long>> {
    public ListLongJsonSerializer() {
    }

    public void serialize(List<Long> longList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (CollUtil.isNotEmpty(longList)) {
            jsonGenerator.writeArray((String[])longList.stream().map((bean) -> {
                return String.valueOf(bean);
            }).toArray((x$0) -> {
                return new String[x$0];
            }), 0, longList.size());
        } else {
            jsonGenerator.writeArray(new String[0], 0, 0);
        }

    }
}
