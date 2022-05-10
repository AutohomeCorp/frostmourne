package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.List;

public class SkywalkingAlarms {

    private Integer total;

    private List<SkywalkingAlarmMessage> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SkywalkingAlarmMessage> getItems() {
        return items;
    }

    public void setItems(List<SkywalkingAlarmMessage> items) {
        this.items = items;
    }
}
