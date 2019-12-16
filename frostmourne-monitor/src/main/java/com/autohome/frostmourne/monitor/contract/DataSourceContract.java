package com.autohome.frostmourne.monitor.contract;

public class DataSourceContract {

    private Long id;

    private String datasource_name;

    private String datasource_type;

    private String service_address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasource_name() {
        return datasource_name;
    }

    public void setDatasource_name(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getDatasource_type() {
        return datasource_type;
    }

    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    public String getService_address() {
        return service_address;
    }

    public void setService_address(String service_address) {
        this.service_address = service_address;
    }
}
