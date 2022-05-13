package com.autohome.frostmourne.monitor.dao.prometheus.domain;

import java.util.List;

public class PrometheusData<T> {

    private String resultType;

    private List<T> result;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
