package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class DepartmentInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.department_name")
    private String departmentName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.full_name")
    private String fullName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modify_at")
    private Date modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.department_name")
    public String getDepartmentName() {
        return departmentName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.department_name")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.011+08:00", comments="Source field: department_info.full_name")
    public String getFullName() {
        return fullName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modify_at")
    public Date getModifyAt() {
        return modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modify_at")
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}