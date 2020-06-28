package com.autohome.frostmourne.monitor.service.core.domain;

import java.util.Map;

public class ElasticsearchMetric {

    private Object metricValue;

    private Map<String, Object> latestDocument;

    public Object getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(Object metricValue) {
        this.metricValue = metricValue;
    }

    public Map<String, Object> getLatestDocument() {
        return latestDocument;
    }

    public void setLatestDocument(Map<String, Object> latestDocument) {
        this.latestDocument = latestDocument;
    }
}
