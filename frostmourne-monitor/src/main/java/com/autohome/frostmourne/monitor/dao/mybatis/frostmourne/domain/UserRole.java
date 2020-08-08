package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class UserRole {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.146+08:00", comments="Source field: user_role.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.account")
    private String account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.role")
    private String role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.15+08:00", comments="Source field: user_role.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.account")
    public String getAccount() {
        return account;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.account")
    public void setAccount(String account) {
        this.account = account;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.role")
    public String getRole() {
        return role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.role")
    public void setRole(String role) {
        this.role = role;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.149+08:00", comments="Source field: user_role.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.15+08:00", comments="Source field: user_role.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.15+08:00", comments="Source field: user_role.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.15+08:00", comments="Source field: user_role.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}