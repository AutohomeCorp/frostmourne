package com.autohome.frostmourne.core.log4j2;

import org.apache.logging.log4j.core.LogEvent;

/**
 * Created by kcq on 2017/6/8.
 */
public abstract class AbstractFieldParser {
    public abstract String parse(String fieldName, LogEvent logEvent);
}
