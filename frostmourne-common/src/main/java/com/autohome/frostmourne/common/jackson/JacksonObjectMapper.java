package com.autohome.frostmourne.common.jackson;

import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonObjectMapper {

    public static ObjectMapper getCommonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return settingCommonObjectMapper(objectMapper);
    }

    public static ObjectMapper settingCommonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.setDateFormat(new StdDateFormat());
        objectMapper.setTimeZone(TimeZone.getDefault());

        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(MapperFeature.INFER_PROPERTY_MUTATORS, false);
        objectMapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
