package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

public class SkywalkingAlarmMessage {

    private Long startTime;

    private String scope;

    private String key;

    private String message;

    private List<SkywalkingEvent> events;

    private List<SkywalkingKeyValue> tags;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SkywalkingEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SkywalkingEvent> events) {
        this.events = events;
    }

    public List<SkywalkingKeyValue> getTags() {
        return tags;
    }

    public void setTags(List<SkywalkingKeyValue> tags) {
        this.tags = tags;
    }
}
