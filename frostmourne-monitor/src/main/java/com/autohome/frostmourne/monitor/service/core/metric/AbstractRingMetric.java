package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.domain.ReferenceBag;
import com.autohome.frostmourne.monitor.tool.MathUtils;
import org.joda.time.DateTime;

public abstract class AbstractRingMetric extends AbstractBaseMetric {

    public abstract MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings);

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> resultMap = new HashMap<>();
        String periodUnit = findPeriodUnit(ruleSettings);
        DateTime now = DateTime.now();
        DateTime end = findEnd(now, periodUnit);
        DateTime start = findStart(end, periodUnit);
        resultMap.put("startTime", start.toDateTimeISO().toString());
        resultMap.put("endTime", end.toDateTimeISO().toString());
        MetricData metricData = pullMetricData(start, end, metricContract, ruleSettings);
        Double current = MathUtils.toDouble(metricData.getMetricValue(), 0D);
        resultMap.put("CURRENT", current);

        List<ReferenceBag> referenceDataList = new ArrayList<>();
        ReferenceBag referenceBag = calculateReference(current, start, end, periodUnit, metricContract, ruleSettings);
        referenceDataList.add(referenceBag);
        resultMap.put("REFERENCE_LIST", referenceDataList);
        return resultMap;
    }

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.ring_compare;
    }

    /**
     * 获取环比周期。(week, day, hour, minute)
     *
     * @param ruleSettings
     * @return period unit
     */
    private String findPeriodUnit(Map<String, String> ruleSettings) {
        return ruleSettings.get("PERIOD_UNIT");
    }

    /**
     * 获取比较类型。(increase, decrease, both)
     *
     * @param ruleSettings
     * @return compare type
     */
    private String findCompareType(Map<String, String> ruleSettings) {
        return ruleSettings.get("COMPARE_TYPE");
    }

    private DateTime findEnd(DateTime now, String periodUnit) {
        DateTime end;
        if (periodUnit.equalsIgnoreCase("hour")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), 0);
        } else if (periodUnit.equalsIgnoreCase("minute")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now.getMinuteOfHour());
        } else if (periodUnit.equalsIgnoreCase("week")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0);
        } else if (periodUnit.equalsIgnoreCase("day")) {
            end = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0);
        } else {
            throw new IllegalArgumentException("unknown ring Unit: " + periodUnit);
        }
        return end;
    }

    private DateTime findStart(DateTime end, String periodUnit) {
        DateTime start;
        if (periodUnit.equalsIgnoreCase("hour")) {
            start = end.minusHours(1);
        } else if (periodUnit.equalsIgnoreCase("minute")) {
            start = end.minusMinutes(1);
        } else if (periodUnit.equalsIgnoreCase("week")) {
            start = end.minusWeeks(1);
        } else if (periodUnit.equalsIgnoreCase("day")) {
            start = end.minusDays(1);
        } else {
            throw new IllegalArgumentException("unknown ring Unit: " + periodUnit);
        }
        return start;
    }

    private ReferenceBag calculateReference(Double current, DateTime start, DateTime end, String ringUnit, MetricContract metricContract,
        Map<String, String> ruleSettings) {
        ReferenceBag referenceData = new ReferenceBag();
        referenceData.setReferenceType(ringUnit);
        DateTime referenceStart;
        DateTime referenceEnd;
        if (ringUnit.equalsIgnoreCase("hour")) {
            referenceStart = start.minusMinutes(60);
            referenceEnd = end.minusMinutes(60);
        } else if (ringUnit.equalsIgnoreCase("minute")) {
            referenceStart = start.minusSeconds(60);
            referenceEnd = end.minusSeconds(60);
        } else if (ringUnit.equalsIgnoreCase("week")) {
            referenceStart = start.minusDays(7);
            referenceEnd = end.minusDays(7);
        } else if (ringUnit.equalsIgnoreCase("day")) {
            referenceStart = start.minusDays(1);
            referenceEnd = end.minusDays(1);
        } else {
            throw new IllegalArgumentException("unknown ring Unit: " + ringUnit);
        }

        MetricData metricData = pullMetricData(referenceStart, referenceEnd, metricContract, ruleSettings);
        Double reference = MathUtils.toDouble(metricData.getMetricValue(), 0D);

        referenceData.setValue(reference);
        referenceData.setPercentage(MathUtils.calculatePercentage(current, reference));

        return referenceData;
    }
}
