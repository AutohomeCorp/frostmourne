package com.autohome.frostmourne.monitor.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathUtils.class);

    public static Double toDouble(Object value, Double defaultValue) {
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception ex) {
            LOGGER.error("error when toDouble, value: " + value.toString(), ex);
            return defaultValue;
        }
    }

    public static Double calculatePercentage(Double current, Double reference) {
        if (reference == 0) {
            return (current - reference) * 100 / (reference + 1);
        }
        return (current - reference) * 100 / reference;
    }
}
