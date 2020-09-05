package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class ServiceInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.178+08:00", comments="Source field: service_info.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.service_name")
    private String serviceName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.remark")
    private String remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.owner")
    private String owner;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modify_at")
    private Date modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.service_name")
    public String getServiceName() {
        return serviceName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.service_name")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.remark")
    public String getRemark() {
        return remark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.owner")
    public String getOwner() {
        return owner;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.182+08:00", comments="Source field: service_info.owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modify_at")
    public Date getModifyAt() {
        return modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.183+08:00", comments="Source field: service_info.modify_at")
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}