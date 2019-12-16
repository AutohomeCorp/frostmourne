package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class DataName {
    private Long id;

    private String data_name;

    private String display_name;

    private Long data_source_id;

    private String timestamp_field;

    private String properties;

    private String creator;

    private Date create_at;

    private String modifier;

    private Date modify_at;

    private String datasource_type;

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
        this.data_name = data_name == null ? null : data_name.trim();
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name == null ? null : display_name.trim();
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
        this.timestamp_field = timestamp_field == null ? null : timestamp_field.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModify_at() {
        return modify_at;
    }

    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }

    public String getDatasource_type() {
        return datasource_type;
    }

    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type == null ? null : datasource_type.trim();
    }
}