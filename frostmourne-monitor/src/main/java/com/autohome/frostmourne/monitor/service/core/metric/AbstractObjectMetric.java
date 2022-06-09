package com.autohome.frostmourne.monitor.service.core.metric;

import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;

public abstract class AbstractObjectMetric extends AbstractBaseMetric {

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.object;
    }
}
