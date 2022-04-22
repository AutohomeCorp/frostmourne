package com.autohome.frostmourne.core.log4j2;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * Created by kcq on 2018/1/3.
 */
@Plugin(name = "DefaultLog4jFieldFactory", category = Node.CATEGORY, printObject = true)
public class DefaultLog4jFieldFactory extends AbstractLifeCycle implements FieldFactory {
    private static final Map<String, LayoutField> DEFAULT_FIELD_MAP = new HashMap<String, LayoutField>() {
        {
            put(FieldName.LOG_AT, LayoutField.LOG_AT);
            put(FieldName.TRACE_ID, LayoutField.TRACE_ID);
            put(FieldName.HOST, LayoutField.HOST);
            put(FieldName.SERVER_IP, LayoutField.SERVER_IP);
            put(FieldName.URI_STEM, LayoutField.URI_STEM);
            put(FieldName.QUERY_STRING, LayoutField.QUERY_STRING);
            put(FieldName.FORM_STRING, LayoutField.FORM_STRING);
            put(FieldName.USER_AGENT, LayoutField.USER_AGENT);
            put(FieldName.LEVEL, LayoutField.LEVEL);
            put(FieldName.CLASS_NAME, LayoutField.CLASS_NAME);
            put(FieldName.METHOD_NAME, LayoutField.METHOD_NAME);
            put(FieldName.METHOD_PARAMS, LayoutField.METHOD_PARAMS);
            put(FieldName.LINE, LayoutField.LINE);
            put(FieldName.LOGGER, LayoutField.LOGGER);
            put(FieldName.EXCEPTION_TYPE, LayoutField.EXCEPTION_TYPE);
            put(FieldName.EXCEPTION_MESSAGE, LayoutField.EXCEPTION_MESSAGE);
            put(FieldName.CUSTOM_MESSAGE, LayoutField.CUSTOM_MESSAGE);
            put(FieldName.STACK_TRACE, LayoutField.STACK_TRACE);
            put(FieldName.THREAD_ID, LayoutField.THREAD_ID);
            put(FieldName.THREAD_NAME, LayoutField.THREAD_NAME);
        }
    };

    @PluginFactory
    public static DefaultLog4jFieldFactory createDefaultFieldFactory() {
        return new DefaultLog4jFieldFactory();
    }

    protected Map<String, LayoutField> fetchCustomFieldMap() {
        return null;
    }

    @Override
    public LayoutField fetchField(String name) {
        Map<String, LayoutField> customMaps = fetchCustomFieldMap();
        if (customMaps != null && customMaps.size() > 0 && customMaps.containsKey(name)) {
            return customMaps.get(name);
        } else {
            return DEFAULT_FIELD_MAP.get(name);
        }
    }
}
