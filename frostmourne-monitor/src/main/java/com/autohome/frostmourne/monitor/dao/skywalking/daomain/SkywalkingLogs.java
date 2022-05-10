package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:29
 */
public class SkywalkingLogs {

    private List<SkywalkingLog> logs;

    private Integer total;

    public List<SkywalkingLog> getLogs() {
        return logs;
    }

    public void setLogs(List<SkywalkingLog> logs) {
        this.logs = logs;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
