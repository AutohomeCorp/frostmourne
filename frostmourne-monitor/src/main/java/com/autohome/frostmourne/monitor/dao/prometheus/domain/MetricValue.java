package com.autohome.frostmourne.monitor.dao.prometheus.domain;

import java.util.List;
import java.util.Map;

public class MetricValue {

    private Map<String, Object> metric;

    private List<Object> value;

    public Map<String, Object> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, Object> metric) {
        this.metric = metric;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }
}
