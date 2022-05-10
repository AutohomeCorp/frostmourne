package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:25
 */
public class SkywalkingLog {

    private String serviceName;

    private String serviceId;

    private String serviceInstanceName;

    private String serviceInstanceId;

    private String endpointName;

    private String traceId;

    private Long timestamp;

    private String contentType;

    private String content;

    private List<SkywalkingTag> tags;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceInstanceName() {
        return serviceInstanceName;
    }

    public void setServiceInstanceName(String serviceInstanceName) {
        this.serviceInstanceName = serviceInstanceName;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SkywalkingTag> getTags() {
        return tags;
    }

    public void setTags(List<SkywalkingTag> tags) {
        this.tags = tags;
    }
}
