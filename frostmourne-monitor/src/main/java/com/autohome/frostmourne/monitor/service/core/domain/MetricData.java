package com.autohome.frostmourne.monitor.service.core.domain;

import java.util.List;
import java.util.Map;

public class MetricData {

    private Object metricValue;

    private Map<String, Object> latestDocument;

    private Map<String, Object> context;

    private List<BucketInfo> buckets;

    private List<Map<String, Object>> topNDocuments;

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

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public List<BucketInfo> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<BucketInfo> buckets) {
        this.buckets = buckets;
    }

    public List<Map<String, Object>> getTopNDocuments() {
        return topNDocuments;
    }

    public void setTopNDocuments(List<Map<String, Object>> topNDocuments) {
        this.topNDocuments = topNDocuments;
    }
}
