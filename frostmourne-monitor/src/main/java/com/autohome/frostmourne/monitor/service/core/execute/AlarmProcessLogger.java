package com.autohome.frostmourne.monitor.service.core.execute;

import java.util.Map;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import org.joda.time.DateTime;

public class AlarmProcessLogger {

    private static final String LINE = System.getProperty("line.separator");

    private final StringBuilder trace = new StringBuilder();

    private ExecuteStatus executeStatus;

    private Long alarmId;

    private AlarmContract alarmContract;

    private Boolean isAlert;

    private String alertMessage;

    private Map<String, Object> context;

    private DateTime start;

    private DateTime end;

    private AlarmLog alarmLog;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public Boolean getAlert() {
        return isAlert;
    }

    public void setAlert(Boolean alert) {
        isAlert = alert;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public ExecuteStatus getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(ExecuteStatus executeStatus) {
        this.executeStatus = executeStatus;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public AlarmContract getAlarmContract() {
        return alarmContract;
    }

    public void setAlarmContract(AlarmContract alarmContract) {
        this.alarmContract = alarmContract;
    }

    public AlarmLog getAlarmLog() {
        return alarmLog;
    }

    public void setAlarmLog(AlarmLog alarmLog) {
        this.alarmLog = alarmLog;
    }

    public void trace(String message) {
        this.trace.append(currentTimeString()).append(" ").append(message).append(LINE);
    }

    public void trace(String format, Object... args) {
        String message = String.format(format, args);
        trace(message);
    }

    public String traceInfo() {
        return this.trace.toString();
    }

    private String currentTimeString() {
        return DateTime.now().toString("[yyyy-MM-dd HH:mm:ss]");
    }
}
