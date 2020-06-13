package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;

public abstract class AbstractCountMetric implements IMetric {

    public Integer findTimeWindowInMinutes(Map<String, String> settings) {
        return Integer.parseInt(settings.get("TIME_WINDOW"));
    }
}
