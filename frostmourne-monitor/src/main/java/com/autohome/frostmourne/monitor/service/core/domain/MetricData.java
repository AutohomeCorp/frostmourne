package com.autohome.frostmourne.monitor.service.core.domain;

import java.util.Map;

public class MetricData {

    private Object metricValue;

    private Map<String, Object> latestDocument;

    private Map<String, String> context;

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

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
