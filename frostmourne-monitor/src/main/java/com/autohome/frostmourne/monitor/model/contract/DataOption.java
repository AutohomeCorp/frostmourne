package com.autohome.frostmourne.monitor.model.contract;

import java.util.List;

public class DataOption {

    private String datasourceType;

    private List<DataSourceOption> dataSourceOptionList;

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType;
    }

    public List<DataSourceOption> getDataSourceOptionList() {
        return dataSourceOptionList;
    }

    public void setDataSourceOptionList(List<DataSourceOption> dataSourceOptionList) {
        this.dataSourceOptionList = dataSourceOptionList;
    }
}
