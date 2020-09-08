package com.autohome.frostmourne.monitor.contract;

import java.util.Date;
import java.util.Map;

public class DataNameContract {

    private Long id;

    private String data_name;

    private String display_name;

    private Long data_source_id;

    private String datasource_type;

    private String timestamp_field;

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

    public String getDataName() {
        return data_name;
    }

    public void setDataName(String data_name) {
        this.data_name = data_name;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String display_name) {
        this.display_name = display_name;
    }

    public Long getDataSourceId() {
        return data_source_id;
    }

    public void setDataSourceId(Long data_source_id) {
        this.data_source_id = data_source_id;
    }

    public String getTimestampField() {
        return timestamp_field;
    }

    public void setTimestampField(String timestamp_field) {
        this.timestamp_field = timestamp_field;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public String getDatasourceType() {
        return datasource_type;
    }

    public void setDatasourceType(String datasource_type) {
        this.datasource_type = datasource_type;
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
