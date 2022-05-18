package com.autohome.frostmourne.common.contract;

import java.io.Serializable;
import java.util.List;

public class PagerContract<T> implements Serializable {
    private static final long serialVersionUID = 8938161129268809414L;

    private int rowcount;
    private int pagecount;
    private int pageindex;
    private List<T> list;

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagerContract() {}

    public PagerContract(List<T> list, int pageSize, int pageIndex, int rowCount) {
        this.list = list;
        this.rowcount = rowCount;
        this.pagecount = getPageCount(rowCount, pageSize);
        this.pageindex = pageIndex;
    }

    private int getPageCount(int rowCount, int pageSize) {
        int tempPageSize = pageSize;
        if (tempPageSize <= 0) {
            tempPageSize = 10;
        }
        return rowCount % tempPageSize == 0 ? rowCount / tempPageSize : (rowCount / tempPageSize) + 1;
    }
}
