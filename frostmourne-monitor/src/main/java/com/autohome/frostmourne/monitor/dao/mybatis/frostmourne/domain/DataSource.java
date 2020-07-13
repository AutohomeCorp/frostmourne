package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class DataSource {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source field: data_source.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_name")
    private String datasource_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_type")
    private String datasource_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.service_address")
    private String service_address;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modify_at")
    private Date modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.properties")
    private String properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source field: data_source.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_name")
    public String getDatasource_name() {
        return datasource_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_name")
    public void setDatasource_name(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_type")
    public String getDatasource_type() {
        return datasource_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.datasource_type")
    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.service_address")
    public String getService_address() {
        return service_address;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.service_address")
    public void setService_address(String service_address) {
        this.service_address = service_address;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modify_at")
    public Date getModify_at() {
        return modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.modify_at")
    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.properties")
    public String getProperties() {
        return properties;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.794+08:00", comments="Source field: data_source.properties")
    public void setProperties(String properties) {
        this.properties = properties;
    }
}