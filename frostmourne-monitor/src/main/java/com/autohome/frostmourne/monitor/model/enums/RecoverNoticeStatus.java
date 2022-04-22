package com.autohome.frostmourne.monitor.model.enums;

public enum RecoverNoticeStatus {

    CLOSE("关闭"), OPEN("开启");

    private String displayName;

    RecoverNoticeStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
