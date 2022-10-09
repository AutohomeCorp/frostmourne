package com.autohome.frostmourne.monitor.dao.prometheus.domain;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 14:44
 */
public class MetricValues {

    private Map<String, Object> metric;

    private List<Object> values;

    public Map<String, Object> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, Object> metric) {
        this.metric = metric;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
