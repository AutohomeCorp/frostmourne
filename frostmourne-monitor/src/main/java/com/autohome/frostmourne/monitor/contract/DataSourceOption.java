package com.autohome.frostmourne.monitor.contract;

import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;

public class DataSourceOption {

    private DataSource dataSource;

    private List<DataNameContract> dataNameContractList;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DataNameContract> getDataNameContractList() {
        return dataNameContractList;
    }

    public void setDataNameContractList(List<DataNameContract> dataNameContractList) {
        this.dataNameContractList = dataNameContractList;
    }
}
