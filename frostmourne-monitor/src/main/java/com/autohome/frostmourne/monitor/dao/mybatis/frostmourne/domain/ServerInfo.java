package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class ServerInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.983+08:00", comments="Source field: server_info.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.986+08:00", comments="Source field: server_info.server_name")
    private String server_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.remark")
    private String remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.modify_at")
    private Date modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.986+08:00", comments="Source field: server_info.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.986+08:00", comments="Source field: server_info.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.986+08:00", comments="Source field: server_info.server_name")
    public String getServer_name() {
        return server_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.server_name")
    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.remark")
    public String getRemark() {
        return remark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.987+08:00", comments="Source field: server_info.modify_at")
    public Date getModify_at() {
        return modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.988+08:00", comments="Source field: server_info.modify_at")
    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }
}