package com.autohome.frostmourne.core.log4j2;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.common.base.Strings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.joda.time.DateTime;

/**
 * Created by kcq on 2017/6/8.
 */
public class LayoutField {
    private static String LOCAL_IP_ADDRESS;
    private final String name;
    private AbstractFieldParser parser;
    private String value;

    public LayoutField(String name, AbstractFieldParser parser) {
        this.name = name;
        this.parser = parser;
    }

    public LayoutField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String format(LogEvent logEvent) {
        if (!Strings.isNullOrEmpty(this.value)) {
            return this.value;
        }
        if (this.parser == null) {
            return null;
        }
        return this.parser.parse(this.name, logEvent);
    }

    public String getName() {
        return this.name;
    }

    public static LayoutField create(String name, AbstractFieldParser fieldParser) {
        return new LayoutField(name, fieldParser);
    }

    public static final LayoutField LOG_AT = create(FieldName.LOG_AT, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return new DateTime(logEvent.getTimeMillis()).toDateTimeISO().toString();
        }
    });

    public static final LayoutField CLASS_NAME = create(FieldName.CLASS_NAME, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getSource() != null ? logEvent.getSource().getClassName() : null;
        }
    });

    public static final LayoutField LINE = create(FieldName.LINE, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getSource() != null ? logEvent.getSource().getLineNumber() + "" : "0";
        }
    });

    public static final LayoutField METHOD_NAME = create(FieldName.METHOD_NAME, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getSource() != null ? logEvent.getSource().getMethodName() : null;
        }
    });

    public static final LayoutField EXCEPTION_TYPE = create(FieldName.EXCEPTION_TYPE, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getThrown() == null) {
                return null;
            }
            Throwable ex = logEvent.getThrown();
            return ex.getClass().getCanonicalName();
        }
    });

    public static final LayoutField EXCEPTION_MESSAGE = create(FieldName.EXCEPTION_MESSAGE, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getThrown() == null) {
                return null;
            }
            return logEvent.getThrown().getMessage();
        }
    });

    public static final LayoutField STACK_TRACE = create(FieldName.STACK_TRACE, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getThrown() == null) {
                return null;
            }
            StringBuilderWriter sbw = new StringBuilderWriter();
            try (PrintWriter pw = new PrintWriter(sbw)) {
                logEvent.getThrown().printStackTrace(pw);
                return sbw.toString();
            }
        }
    });

    public static final LayoutField CUSTOM_MESSAGE = create(FieldName.CUSTOM_MESSAGE, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getMessage() != null ? logEvent.getMessage().getFormattedMessage() : null;
        }
    });

    public static final LayoutField LOGGER = create(FieldName.LOGGER, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getLoggerName();
        }
    });

    public static final LayoutField TRACE_ID = create(FieldName.TRACE_ID, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey("Custom-TraceId")) {
                return logEvent.getContextData().getValue("Custom-TraceId");
            }
            if (logEvent.getContextData().containsKey("X-B3-TraceId")) {
                return logEvent.getContextData().getValue("X-B3-TraceId");
            }
            if (logEvent.getContextData().containsKey("tid")) {
                // skywalking traceid
                return logEvent.getContextData().getValue("tid");
            }
            if (logEvent.getContextData().containsKey("SW-TraceId")) {
                return logEvent.getContextData().getValue("SW-TraceId");
            }
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField HOST = create(FieldName.HOST, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField SERVER_IP = create(FieldName.SERVER_IP, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return LayoutField.findServerIP();
        }
    });

    public static final LayoutField URI_STEM = create(FieldName.URI_STEM, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField QUERY_STRING = create(FieldName.QUERY_STRING, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField FORM_STRING = create(FieldName.FORM_STRING, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getLevel().isMoreSpecificThan(Level.ERROR)
                && logEvent.getContextData().containsKey(fieldName)) {
                String formString = logEvent.getContextData().getValue(fieldName);
                if (formString.length() > 2000) {
                    return formString.substring(0, 2000);
                }
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField LEVEL = create(FieldName.LEVEL, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return logEvent.getLevel().name().toUpperCase();
        }
    });

    public static final LayoutField METHOD_PARAMS = create(FieldName.METHOD_PARAMS, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getThrown() == null) {
                return null;
            }
            return null;
        }
    });

    public static final LayoutField USER_AGENT = create(FieldName.USER_AGENT, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            if (logEvent.getContextData().containsKey(fieldName)) {
                return logEvent.getContextData().getValue(fieldName);
            }
            return null;
        }
    });

    public static final LayoutField THREAD_NAME = create(FieldName.THREAD_NAME, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return Thread.currentThread().getName();
        }
    });

    public static final LayoutField THREAD_ID = create(FieldName.THREAD_ID, new AbstractFieldParser() {
        @Override
        public String parse(String fieldName, LogEvent logEvent) {
            return String.valueOf(Thread.currentThread().getId());
        }
    });

    public static String findServerIP() {
        if (Strings.isNullOrEmpty(LOCAL_IP_ADDRESS)) {
            try {
                LOCAL_IP_ADDRESS = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }
        return LOCAL_IP_ADDRESS;
    }
}
