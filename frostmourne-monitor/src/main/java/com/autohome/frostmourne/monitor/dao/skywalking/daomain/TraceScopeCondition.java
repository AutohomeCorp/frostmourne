package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:31
 */
public class TraceScopeCondition {

    private String traceId;

    private String segmentId;

    private Integer spanId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getSpanId() {
        return spanId;
    }

    public void setSpanId(Integer spanId) {
        this.spanId = spanId;
    }
}
