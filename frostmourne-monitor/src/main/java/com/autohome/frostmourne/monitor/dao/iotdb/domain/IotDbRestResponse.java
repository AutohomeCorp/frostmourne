package com.autohome.frostmourne.monitor.dao.iotdb.domain;

import java.util.List;

public class IotDbRestResponse {

    private List<String> expressions;

    private List<String> columnNames;

    private List<Long> timestamps;

    private List<String> timestampsIso8601;

    private List<Object[]> values;

    public List<String> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<Long> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<Long> timestamps) {
        this.timestamps = timestamps;
    }

    public List<Object[]> getValues() {
        return values;
    }

    public void setValues(List<Object[]> values) {
        this.values = values;
    }

    public List<String> getTimestampsIso8601() {
        return timestampsIso8601;
    }

    public void setTimestampsIso8601(List<String> timestampsIso8601) {
        this.timestampsIso8601 = timestampsIso8601;
    }
}
