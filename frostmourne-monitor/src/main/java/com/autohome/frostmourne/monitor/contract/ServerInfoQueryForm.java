package com.autohome.frostmourne.monitor.contract;

public class ServerInfoQueryForm {

    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    /**
     * 每页条数
     */
    private int pageSize = 20;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
