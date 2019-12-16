package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class AlarmLog {
    private Long id;

    private Long alarm_id;

    private Date exe_start;

    private Date exe_end;

    private Integer cost;

    private String execute_result;

    private String verify_result;

    private Date create_at;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public Date getExe_start() {
        return exe_start;
    }

    public void setExe_start(Date exe_start) {
        this.exe_start = exe_start;
    }

    public Date getExe_end() {
        return exe_end;
    }

    public void setExe_end(Date exe_end) {
        this.exe_end = exe_end;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getExecute_result() {
        return execute_result;
    }

    public void setExecute_result(String execute_result) {
        this.execute_result = execute_result == null ? null : execute_result.trim();
    }

    public String getVerify_result() {
        return verify_result;
    }

    public void setVerify_result(String verify_result) {
        this.verify_result = verify_result == null ? null : verify_result.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}