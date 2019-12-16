package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class Alarm {
    private Long id;

    private String alarm_name;

    private String alarm_type;

    private String description;

    private String owner_key;

    private String status;

    private String execute_result;

    private Date execute_at;

    private Long job_id;

    private String cron;

    private String creator;

    private Date create_at;

    private String modifier;

    private Date modify_at;

    private String team_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarm_name() {
        return alarm_name;
    }

    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name == null ? null : alarm_name.trim();
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type == null ? null : alarm_type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOwner_key() {
        return owner_key;
    }

    public void setOwner_key(String owner_key) {
        this.owner_key = owner_key == null ? null : owner_key.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExecute_result() {
        return execute_result;
    }

    public void setExecute_result(String execute_result) {
        this.execute_result = execute_result == null ? null : execute_result.trim();
    }

    public Date getExecute_at() {
        return execute_at;
    }

    public void setExecute_at(Date execute_at) {
        this.execute_at = execute_at;
    }

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
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

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name == null ? null : team_name.trim();
    }
}