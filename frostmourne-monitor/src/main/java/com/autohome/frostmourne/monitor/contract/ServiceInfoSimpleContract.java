package com.autohome.frostmourne.monitor.contract;

public class ServiceInfoSimpleContract {

    /**
     * ID
     */
    private Long id;
    /**
     * 服务名
     */
    private String serviceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
