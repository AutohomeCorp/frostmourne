package com.autohome.frostmourne.monitor.model.message.ding;

import java.util.List;

public class DingAt {

    private Boolean isAtAll;

    private List<String> atMobiles;

    public Boolean getAtAll() {
        return isAtAll;
    }

    public void setAtAll(Boolean atAll) {
        isAtAll = atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }
}
