package com.autohome.frostmourne.spi.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "frostmourne.spi")
public class FrostmourneSpiProperties {

    private String serviceAddr;

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }
}
