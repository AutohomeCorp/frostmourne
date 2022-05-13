package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:36
 */
public class SkywalkingPagination {

    private Integer pageNum;

    private Integer pageSize;

    private Boolean needTotal;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(Boolean needTotal) {
        this.needTotal = needTotal;
    }
}
