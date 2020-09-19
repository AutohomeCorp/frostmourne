package com.autohome.frostmourne.monitor.dao.influxdb;

import java.util.List;

/**
 * Created by kcq on 2018/6/1.
 */
public class Series {
    private String name;

    private List<String> columns;

    private List<List<Object>> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<List<Object>> getValues() {
        return values;
    }

    public void setValues(List<List<Object>> values) {
        this.values = values;
    }
}
