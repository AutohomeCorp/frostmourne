package com.autohome.frostmourne.monitor.dao.influxdb;

import java.util.List;

/**
 * Created by kcq on 2018/6/1.
 */
public class InfluxdbResponse {

    private List<InfluxdbResult> results;

    public List<InfluxdbResult> getResults() {
        return results;
    }

    public void setResults(List<InfluxdbResult> results) {
        this.results = results;
    }
}
