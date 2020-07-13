package com.autohome.frostmourne.monitor.contract;

import java.util.List;
import java.util.Map;

public class ElasticsearchDataResult {

    private long total;

    private String scrollId;

    private StatItem statItem;

    private List<String> fields;

    private String timestampField;

    private List<String> headFields;

    private List<Map<String, Object>> logs;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public StatItem getStatItem() {
        return statItem;
    }

    public void setStatItem(StatItem statItem) {
        this.statItem = statItem;
    }

    public List<Map<String, Object>> getLogs() {
        return logs;
    }

    public void setLogs(List<Map<String, Object>> logs) {
        this.logs = logs;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getTimestampField() {
        return timestampField;
    }

    public void setTimestampField(String timestampField) {
        this.timestampField = timestampField;
    }

    public List<String> getHeadFields() {
        return headFields;
    }

    public void setHeadFields(List<String> headFields) {
        this.headFields = headFields;
    }
}
