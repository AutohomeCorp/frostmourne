package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

public class SkywalkingAlarmQueryCondition {

    private String keyword;

    private String scope;

    private List<SkywalkingKeyValue> tags;

    private SkywalkingDuration duration;

    private SkywalkingPagination paging;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<SkywalkingKeyValue> getTags() {
        return tags;
    }

    public void setTags(List<SkywalkingKeyValue> tags) {
        this.tags = tags;
    }

    public SkywalkingDuration getDuration() {
        return duration;
    }

    public void setDuration(SkywalkingDuration duration) {
        this.duration = duration;
    }

    public SkywalkingPagination getPaging() {
        return paging;
    }

    public void setPaging(SkywalkingPagination paging) {
        this.paging = paging;
    }
}
