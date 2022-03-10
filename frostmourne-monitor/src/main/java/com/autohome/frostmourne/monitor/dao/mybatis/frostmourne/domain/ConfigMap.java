package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class ConfigMap {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.84+08:00", comments="Source field: config_map.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_key")
    private String configKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_value")
    private String configValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modify_at")
    private Date modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.842+08:00", comments="Source field: config_map.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_key")
    public String getConfigKey() {
        return configKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_key")
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_value")
    public String getConfigValue() {
        return configValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.config_value")
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modify_at")
    public Date getModifyAt() {
        return modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modify_at")
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.844+08:00", comments="Source field: config_map.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}