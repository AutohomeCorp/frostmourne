package com.autohome.frostmourne.monitor.dao.prometheus.domain;

import java.util.List;

public class PrometheusResponse<T> {

    private String status;

    private String errorType;

    private String error;

    private List<String> warnings;

    private PrometheusData<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public PrometheusData<T> getData() {
        return data;
    }

    public void setData(PrometheusData<T> data) {
        this.data = data;
    }
}
