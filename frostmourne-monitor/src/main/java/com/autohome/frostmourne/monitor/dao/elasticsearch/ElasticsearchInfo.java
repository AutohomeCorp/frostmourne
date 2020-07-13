package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.util.Map;

import com.autohome.frostmourne.monitor.contract.DataSourceContract;

public class ElasticsearchInfo {

    private String name;

    private Boolean sniff;

    private String esHostList;

    private Map<String, String> settings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSniff() {
        return sniff;
    }

    public void setSniff(Boolean sniff) {
        this.sniff = sniff;
    }

    public String getEsHostList() {
        return esHostList;
    }

    public void setEsHostList(String esHostList) {
        this.esHostList = esHostList;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public ElasticsearchInfo(DataSourceContract dataSourceContract) {
        this.name = dataSourceContract.getDatasource_name();
        this.esHostList = dataSourceContract.getService_address();
        this.sniff = false;
        this.settings = dataSourceContract.getSettings();
    }
}
