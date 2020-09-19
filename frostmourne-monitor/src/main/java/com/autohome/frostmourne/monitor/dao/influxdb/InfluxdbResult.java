package com.autohome.frostmourne.monitor.dao.influxdb;

import java.util.List;

/**
 * Created by kcq on 2018/6/1.
 */
public class InfluxdbResult {
    private String statement_id;

    private List<Series> series;

    public String getStatement_id() {
        return statement_id;
    }

    public void setStatement_id(String statement_id) {
        this.statement_id = statement_id;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}
