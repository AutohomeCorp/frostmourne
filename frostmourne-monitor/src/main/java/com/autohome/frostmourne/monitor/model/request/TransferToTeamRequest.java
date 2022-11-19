package com.autohome.frostmourne.monitor.model.request;

import java.util.List;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/11/19 16:25
 */
public class TransferToTeamRequest {

    private List<Long> alarmIdList;

    private String newTeamName;

    public List<Long> getAlarmIdList() {
        return alarmIdList;
    }

    public void setAlarmIdList(List<Long> alarmIdList) {
        this.alarmIdList = alarmIdList;
    }

    public String getNewTeamName() {
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName) {
        this.newTeamName = newTeamName;
    }
}
