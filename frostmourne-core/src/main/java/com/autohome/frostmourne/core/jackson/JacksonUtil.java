package com.autohome.frostmourne.core.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
    private static final ObjectMapper COMMON_DEFINE_OBJECT_MAPPER;

    static {
        COMMON_DEFINE_OBJECT_MAPPER = JacksonObjectMapper.getCommonObjectMapper();
    }

    private JacksonUtil() {}

    public static <T> String serialize(T object) {
        return serialize(object, COMMON_DEFINE_OBJECT_MAPPER);
    }

    public static <T> String serialize(T object, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T deSerialize(String jsonString, Class<T> classT) {
        return deSerialize(jsonString, classT, COMMON_DEFINE_OBJECT_MAPPER);
    }

    public static <T> T deSerialize(String jsonString, Class<T> classT, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(jsonString, classT);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T deSerialize(String jsonString, TypeReference<T> typeReference) {
        return deSerialize(jsonString, typeReference, COMMON_DEFINE_OBJECT_MAPPER);
    }

    public static <T> T deSerialize(String jsonString, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T deSerialize(String jsonString, JavaType javaType) {
        return deSerialize(jsonString, javaType, COMMON_DEFINE_OBJECT_MAPPER);
    }

    public static <T> T deSerialize(String jsonString, JavaType javaType, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> List<T> deSerializeList(String jsonString, Class<T> classT) {
        return deSerializeList(jsonString, classT, COMMON_DEFINE_OBJECT_MAPPER);
    }

    public static <T> List<T> deSerializeList(String jsonString, Class<T> classT, ObjectMapper objectMapper) {
        JavaType javaType = getCollectionType(objectMapper, ArrayList.class, classT);
        return deSerialize(jsonString, javaType, objectMapper);
    }

    public static JavaType getCollectionType(ObjectMapper objectMapper, Class<?> collectionClass,
        Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static ObjectMapper mapper() {
        return COMMON_DEFINE_OBJECT_MAPPER;
    }
}
