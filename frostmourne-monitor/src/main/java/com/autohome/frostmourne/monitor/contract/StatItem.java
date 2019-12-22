package com.autohome.frostmourne.monitor.contract;

import java.util.ArrayList;
import java.util.List;

public class StatItem {

    private List<String> keys;

    private List<Double> values;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public StatItem() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }
}
