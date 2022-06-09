package com.autohome.frostmourne.monitor.service.core.metric;

public abstract class AbstractBaseMetric implements IMetric {

    @Override
    public boolean matchMetricType(String metricType) {
        return metricType.equalsIgnoreCase(metricType().name());
    }
}
