package com.autohome.frostmourne.monitor.model.enums;

/**
 * risk level
 *
 * @author Aping
 * @since 2022/5/15 16:12
 */
public enum RiskLevel {

    /**
     * 提示
     */
    info("提示"),

    /**
     * 重要
     */
    important("重要"),

    /**
     * 紧急
     */
    emergency("emergency"),

    /**
     * 我崩了
     */
    crash("crash");

    private final String desc;

    RiskLevel(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
