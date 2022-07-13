package com.dpwgc.message.center.sdk.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        //将对象转为json字符串
        return jsonMapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String json,Class<T> tClass) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(json, tClass);
    }
}
