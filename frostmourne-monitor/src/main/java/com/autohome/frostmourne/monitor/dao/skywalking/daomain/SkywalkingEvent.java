package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

public class SkywalkingEvent {

    private String uuid;

    private SkywalkingSource source;

    private String name;

    private String type;

    private String message;

    private List<SkywalkingKeyValue> parameters;

    private Long startTime;

    private Long endTime;

    private String layer;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public SkywalkingSource getSource() {
        return source;
    }

    public void setSource(SkywalkingSource source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SkywalkingKeyValue> getParameters() {
        return parameters;
    }

    public void setParameters(List<SkywalkingKeyValue> parameters) {
        this.parameters = parameters;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }
}
