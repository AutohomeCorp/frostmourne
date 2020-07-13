package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class DepartmentInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source field: department_info.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source field: department_info.department_name")
    private String department_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.full_name")
    private String full_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modify_at")
    private Date modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source field: department_info.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source field: department_info.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source field: department_info.department_name")
    public String getDepartment_name() {
        return department_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.department_name")
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.full_name")
    public String getFull_name() {
        return full_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.full_name")
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modify_at")
    public Date getModify_at() {
        return modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modify_at")
    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}