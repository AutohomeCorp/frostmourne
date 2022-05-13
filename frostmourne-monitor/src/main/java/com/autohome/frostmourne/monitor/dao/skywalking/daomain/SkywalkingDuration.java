package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:32
 */
public class SkywalkingDuration {

    private String start;

    private String end;

    private String step;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
