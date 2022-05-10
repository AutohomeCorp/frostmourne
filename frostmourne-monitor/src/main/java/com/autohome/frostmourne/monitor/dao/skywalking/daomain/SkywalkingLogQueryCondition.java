package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:30
 */
public class SkywalkingLogQueryCondition {

    private String serviceId;

    private String serviceInstanceId;

    private String endpointId;

    private TraceScopeCondition traceScopeCondition;

    private SkywalkingDuration queryDuration;

    private SkywalkingPagination paging;

    private List<SkywalkingTag> tags;

    private List<String> keywordsOfContent;

    private List<String> excludingKeywordsOfContent;

    private String queryOrder;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public TraceScopeCondition getTraceScopeCondition() {
        return traceScopeCondition;
    }

    public void setTraceScopeCondition(TraceScopeCondition traceScopeCondition) {
        this.traceScopeCondition = traceScopeCondition;
    }

    public SkywalkingDuration getQueryDuration() {
        return queryDuration;
    }

    public void setQueryDuration(SkywalkingDuration queryDuration) {
        this.queryDuration = queryDuration;
    }

    public SkywalkingPagination getPaging() {
        return paging;
    }

    public void setPaging(SkywalkingPagination paging) {
        this.paging = paging;
    }

    public List<SkywalkingTag> getTags() {
        return tags;
    }

    public void setTags(List<SkywalkingTag> tags) {
        this.tags = tags;
    }

    public List<String> getKeywordsOfContent() {
        return keywordsOfContent;
    }

    public void setKeywordsOfContent(List<String> keywordsOfContent) {
        this.keywordsOfContent = keywordsOfContent;
    }

    public List<String> getExcludingKeywordsOfContent() {
        return excludingKeywordsOfContent;
    }

    public void setExcludingKeywordsOfContent(List<String> excludingKeywordsOfContent) {
        this.excludingKeywordsOfContent = excludingKeywordsOfContent;
    }

    public String getQueryOrder() {
        return queryOrder;
    }

    public void setQueryOrder(String queryOrder) {
        this.queryOrder = queryOrder;
    }
}
