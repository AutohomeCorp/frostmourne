package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import org.joda.time.DateTime;

public abstract class AbstractSameTimeMetric implements IMetric {

    /**
     * 获取间隔单位；HOUR: 小时；DAY: 天
     *
     * @param settings rule settings
     * @return period unit value
     */
    protected String findPeriodUnit(Map<String, String> settings) {
        return settings.get("PERIOD_UNIT");
    }

    /**
     * 时间间隔中文说明
     *
     * @param periodUnit periodUnit
     * @return period unit description
     */
    protected String findPeriodUnitDescription(String periodUnit) {
        if (periodUnit.equalsIgnoreCase("HOUR")) {
            return "小时";
        } else if (periodUnit.equalsIgnoreCase("DAY")) {
            return "天";
        } else {
            throw new IllegalArgumentException("unknown period unit: " + periodUnit);
        }
    }

    /**
     * 根据当前时间和间隔类型获取查询范围的结束时间
     *
     * @param now        当前时间
     * @param periodUnit 时间间隔
     * @return 查询范围的结束时间
     */
    protected DateTime findEnd(DateTime now, String periodUnit) {
        DateTime end;
        if (periodUnit.equalsIgnoreCase("HOUR")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), 0);
        } else if (periodUnit.equalsIgnoreCase("DAY")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0);
        } else {
            throw new IllegalArgumentException("unknown period unit: " + periodUnit);
        }
        return end;
    }

    /**
     * 根据结束时间和比较间隔类型获取开始时间
     *
     * @param end        结束时间
     * @param periodUnit 间隔类型
     * @return start time
     */
    protected DateTime findStart(DateTime end, String periodUnit) {
        DateTime start;
        if (periodUnit.equalsIgnoreCase("HOUR")) {
            start = end.minusMinutes(60);
        } else if (periodUnit.equalsIgnoreCase("DAY")) {
            start = end.minusDays(1);
        } else {
            throw new IllegalArgumentException("unknown period unit: " + periodUnit);
        }
        return start;
    }

    /**
     * 获取同比比较的对比类型列表
     *
     * @param settings rule settings
     * @return 比较类型列表
     */
    protected List<String> findReferenceTypeList(Map<String, String> settings) {
        String referString = settings.get("REFERENCE_TYPE_LIST");
        return Splitter.on(',').splitToList(referString);
    }
}
