package com.autohome.frostmourne.monitor.contract;

import java.util.Date;
import java.util.Map;

public class DataSourceContract {

    private Long id;

    private String datasource_name;

    private String datasource_type;

    private String service_address;

    private Map<String, String> settings;

    private String creator;

    private Date create_at;

    private String modifier;

    private Date modify_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasourceName() {
        return datasource_name;
    }

    public void setDatasourceName(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getDatasourceType() {
        return datasource_type;
    }

    public void setDatasourceType(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    public String getServiceAddress() {
        return service_address;
    }

    public void setServiceAddress(String service_address) {
        this.service_address = service_address;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateAt() {
        return create_at;
    }

    public void setCreateAt(Date create_at) {
        this.create_at = create_at;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyAt() {
        return modify_at;
    }

    public void setModifyAt(Date modify_at) {
        this.modify_at = modify_at;
    }
}
