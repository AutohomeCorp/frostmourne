package com.autohome.frostmourne.monitor.service.core.execute;

import java.util.Map;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.model.constant.GlobalConstant;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import org.joda.time.DateTime;

public class AlarmProcessLogger {

    private final StringBuilder trace = new StringBuilder();

    private ExecuteStatus executeStatus;

    private AlarmContract alarmContract;

    private Boolean isAlert;

    private String alertMessage;

    private Map<String, String> eventMd5;

    private Map<String, Object> context;

    private DateTime start;

    private DateTime end;

    private AlarmLog alarmLog;

    public void trace(String message) {
        this.trace.append(DateTime.now().toString("[yyyy-MM-dd HH:mm:ss]")).append(" ").append(message).append(GlobalConstant.LINE_SEPARATOR);
    }

    public void trace(String format, Object... args) {
        String message = String.format(format, args);
        trace(message);
    }

    public String traceInfo() {
        return this.trace.toString();
    }

    public StringBuilder getTrace() {
        return trace;
    }

    public ExecuteStatus getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(ExecuteStatus executeStatus) {
        this.executeStatus = executeStatus;
    }

    public AlarmContract getAlarmContract() {
        return alarmContract;
    }

    public void setAlarmContract(AlarmContract alarmContract) {
        this.alarmContract = alarmContract;
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

    public Map<String, String> getEventMd5() {
        return eventMd5;
    }

    public void setEventMd5(Map<String, String> eventMd5) {
        this.eventMd5 = eventMd5;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
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

    public AlarmLog getAlarmLog() {
        return alarmLog;
    }

    public void setAlarmLog(AlarmLog alarmLog) {
        this.alarmLog = alarmLog;
    }
}
