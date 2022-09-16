package com.autohome.frostmourne.monitor.model.contract;

import java.util.Date;
import java.util.Map;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;

public class DataSourceContract {

    private Long id;

    private String datasourceName;

    private DataSourceType datasourceType;

    private String serviceAddress;

    private Map<String, String> settings;

    private String creator;

    private Date createAt;

    private String modifier;

    private Date modifyAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public DataSourceType getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(DataSourceType datasourceType) {
        this.datasourceType = datasourceType;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
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
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Override
    public String toString() {
        return "DataSourceContract{" +
                "id=" + id +
                ", datasourceName='" + datasourceName + '\'' +
                ", datasourceType=" + datasourceType +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", settings=" + settings +
                ", creator='" + creator + '\'' +
                ", createAt=" + createAt +
                ", modifier='" + modifier + '\'' +
                ", modifyAt=" + modifyAt +
                '}';
    }
}
