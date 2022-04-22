package com.autohome.frostmourne.monitor.model.enums;

public enum ExecuteStatus {
    /*
     * 新创建的Alarm，一次都没有执行*/
    WAITING(0, "WAITING"),
    /*
     * 执行成功*/
    SUCCESS(1, "SUCCESS"),
    /*
     * 正在执行*/
    RUNNING(2, "RUNNING"),
    /*
     * 执行过程出错*/
    ERROR(3, "ERROR"),;

    private int status;
    private String name;

    ExecuteStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}