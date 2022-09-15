package com.autohome.frostmourne.monitor.model.contract;

import lombok.Data;

import java.util.List;

@Data
public class DataOption {

    private String datasourceType;

    private List<DataSourceOption> dataSourceOptionList;
}
