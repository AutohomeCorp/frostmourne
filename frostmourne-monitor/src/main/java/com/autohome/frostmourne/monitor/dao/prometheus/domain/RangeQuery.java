package com.autohome.frostmourne.monitor.dao.prometheus.domain;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/9/30 15:44
 */
public class RangeQuery {

    private String query;

    private String start;

    private String end;

    private String step;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

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
