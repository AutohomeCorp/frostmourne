package com.autohome.frostmourne.monitor.dao.elasticsearch;

public class ElasticsearchInfo {

    private String name;

    private Boolean sniff;

    private String esHostList;

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
}
