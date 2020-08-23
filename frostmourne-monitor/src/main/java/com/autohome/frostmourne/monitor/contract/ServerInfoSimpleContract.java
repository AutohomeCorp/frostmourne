package com.autohome.frostmourne.monitor.contract;

public class ServerInfoSimpleContract {

    /**
     * ID
     */
    private Long id;
    /**
     * 服务名
     */
    private String serverName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
