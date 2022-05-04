package com.autohome.frostmourne.monitor.service.core.domain;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/3 13:00
 */
public class BucketInfo {

    private String key;

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
