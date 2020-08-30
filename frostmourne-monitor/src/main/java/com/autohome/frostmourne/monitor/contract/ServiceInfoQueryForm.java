package com.autohome.frostmourne.monitor.contract;

public class ServiceInfoQueryForm {

    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 负责人
     */
    private String owner;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    /**
     * 每页条数
     */
    private int pageSize = 20;
    /**
     * 排序类型
     */
    private String orderType;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
