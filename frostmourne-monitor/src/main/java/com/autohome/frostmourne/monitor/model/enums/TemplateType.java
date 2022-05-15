package com.autohome.frostmourne.monitor.model.enums;

/**
 * template type
 *
 * @author Aping
 * @since 2022/5/15 16:27
 */
public enum TemplateType {
    /**
     * 通用模板
     */
    COMMON("通用"),
    /**
     * 数据源模板
     */
    DATA_NAME("数据");

    private final String displayName;

    TemplateType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
