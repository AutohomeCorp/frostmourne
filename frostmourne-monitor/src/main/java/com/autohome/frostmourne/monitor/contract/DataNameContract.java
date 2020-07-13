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

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Long getData_source_id() {
        return data_source_id;
    }

    public void setData_source_id(Long data_source_id) {
        this.data_source_id = data_source_id;
    }

    public String getTimestamp_field() {
        return timestamp_field;
    }

    public void setTimestamp_field(String timestamp_field) {
        this.timestamp_field = timestamp_field;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public String getDatasource_type() {
        return datasource_type;
    }

    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModify_at() {
        return modify_at;
    }

    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }
}
