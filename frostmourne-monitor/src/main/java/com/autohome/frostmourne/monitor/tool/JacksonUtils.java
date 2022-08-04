package com.autohome.frostmourne.monitor.tool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <p>
 * serialize/deserialize util
 * </p>
 *
 * @author Aping
 * @since 2022/05/03 14:02
 */
public class JacksonUtils {

    private static final String OBJECT_MAPPER_BEAN_ID = "objectMapper";

    private static final String MSG_FOR_SERIALIZE_CLASS = "serialize for class [%s] failed: [%s]";

    private static final String MSG_FOR_DESERIALIZE_CLASS = "deserialize for class [%s] failed: [%s]";

    static ObjectMapper mapper;

    static {
        mapper = SpringApplicationUtils.getContext().getBean(ObjectMapper.class, OBJECT_MAPPER_BEAN_ID);
    }

    /**
     * Object to json string.
     *
     * @param obj obj
     * @return json string
     * @throws RuntimeException if transfer failed
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format(MSG_FOR_SERIALIZE_CLASS, obj.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Object to json string byte array.
     *
     * @param obj obj
     * @return json string byte array
     * @throws RuntimeException if transfer failed
     */
    public static byte[] toJsonBytes(Object obj) {
        try {
            return mapper.writeValueAsString(obj).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format(MSG_FOR_SERIALIZE_CLASS, obj.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls class of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(byte[] json, Class<T> cls) {
        try {
            return toObj(StringUtils.toEncodedString(json, StandardCharsets.UTF_8), cls);
        } catch (Exception e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, cls.getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls {@link Type} of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(byte[] json, Type cls) {
        try {
            return toObj(StringUtils.toEncodedString(json, StandardCharsets.UTF_8), cls);
        } catch (Exception e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, cls.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param inputStream json string input stream
     * @param cls class of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(InputStream inputStream, Class<T> cls) {
        try {
            return mapper.readValue(inputStream, cls);
        } catch (IOException e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, cls.getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string byte array
     * @param typeReference {@link TypeReference} of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(byte[] json, TypeReference<T> typeReference) {
        try {
            return toObj(StringUtils.toEncodedString(json, StandardCharsets.UTF_8), typeReference);
        } catch (Exception e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, typeReference.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param cls class of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, cls.getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param type {@link Type} of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(String json, Type type) {
        try {
            return mapper.readValue(json, mapper.constructType(type));
        } catch (IOException e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, type.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param json json string
     * @param typeReference {@link TypeReference} of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, typeReference.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Object.
     *
     * @param inputStream json string input stream
     * @param type {@link Type} of object
     * @param <T> General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T toObj(InputStream inputStream, Type type) {
        try {
            return mapper.readValue(inputStream, mapper.constructType(type));
        } catch (IOException e) {
            throw new RuntimeException(String.format(MSG_FOR_DESERIALIZE_CLASS, type.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Json string deserialize to Jackson {@link JsonNode}.
     *
     * @param json json string
     * @return {@link JsonNode}
     * @throws RuntimeException if deserialize failed
     */
    public static JsonNode toObj(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Register sub type for child class.
     *
     * @param clz child class
     * @param type type name of child class
     */
    public static void registerSubtype(Class<?> clz, String type) {
        mapper.registerSubtypes(new NamedType(clz, type));
    }

    /**
     * Create a new empty Jackson {@link ObjectNode}.
     *
     * @return {@link ObjectNode}
     */
    public static ObjectNode createEmptyJsonNode() {
        return new ObjectNode(mapper.getNodeFactory());
    }

    /**
     * Create a new empty Jackson {@link ArrayNode}.
     *
     * @return {@link ArrayNode}
     */
    public static ArrayNode createEmptyArrayNode() {
        return new ArrayNode(mapper.getNodeFactory());
    }

    /**
     * Parse object to Jackson {@link JsonNode}.
     *
     * @param obj object
     * @return {@link JsonNode}
     */
    public static JsonNode transferToJsonNode(Object obj) {
        return mapper.valueToTree(obj);
    }

    /**
     * construct java type -> Jackson Java Type.
     *
     * @param type java type
     * @return JavaType {@link JavaType}
     */
    public static JavaType constructJavaType(Type type) {
        return mapper.constructType(type);
    }

}
