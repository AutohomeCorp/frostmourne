package com.autohome.frostmourne.monitor.contract;

import java.util.Map;

public class RuleContract {

    private String rule_type;

    private Long alarm_id;

    private String alert_template;

    private Map<String, String> settings;

    public String getRule_type() {
        return rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlert_template() {
        return alert_template;
    }

    public void setAlert_template(String alert_template) {
        this.alert_template = alert_template;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }
}
