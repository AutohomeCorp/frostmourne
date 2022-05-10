package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

public class SkywalkingSource {

    private String service;

    private String serviceInstance;

    private String endpoint;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(String serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
