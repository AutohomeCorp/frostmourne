package com.autohome.frostmourne.monitor.service.core.metric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.domain.ReferenceBag;
import com.google.common.base.Splitter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSameTimeMetric implements IMetric {

    public abstract MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings);

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSameTimeMetric.class);

    /**
     * 获取间隔单位；HOUR: 小时；DAY: 天
     *
     * @param ruleSettings rule settings
     * @return period unit value
     */
    protected String findPeriodUnit(Map<String, String> ruleSettings) {
        return ruleSettings.get("PERIOD_UNIT");
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
     * @param ruleSettings rule settings
     * @return 比较类型列表
     */
    protected List<String> findReferenceTypeList(Map<String, String> ruleSettings) {
        String referString = ruleSettings.get("REFERENCE_TYPE_LIST");
        return Splitter.on(',').splitToList(referString);
    }


    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> resultMap = new HashMap<>();
        String periodUnit = findPeriodUnit(ruleSettings);
        DateTime now = DateTime.now();
        DateTime end = findEnd(now, periodUnit);
        DateTime start = findStart(end, periodUnit);
        resultMap.put("startTime", start.toDateTimeISO().toString());
        resultMap.put("endTime", end.toDateTimeISO().toString());
        resultMap.put("PERIOD_UNIT_DESCRIPTION", findPeriodUnitDescription(periodUnit));
        Double current = null;

        MetricData elasticsearchMetric = pullMetricData(start, end, metricContract, ruleSettings);
        current = toDouble(elasticsearchMetric.getMetricValue(), 0D);
        resultMap.put("CURRENT", current);
        List<String> referenceTypeList = findReferenceTypeList(ruleSettings);
        List<ReferenceBag> referenceDataList = new ArrayList<>();
        try {
            for (String referenceType : referenceTypeList) {
                ReferenceBag referenceBag = calculateReference(start, end, referenceType, metricContract, current, ruleSettings);
                referenceDataList.add(referenceBag);
            }
            resultMap.put("REFERENCE_LIST", referenceDataList);
        } catch (
                IOException ex) {
            throw new RuntimeException("error when calculateReference", ex);
        }

        return resultMap;
    }

    private ReferenceBag calculateReference(DateTime start, DateTime end, String referenceType,
                                            MetricContract metricContract, Double current, Map<String, String> ruleSettings) throws IOException {
        ReferenceBag referenceBag = new ReferenceBag();
        referenceBag.setReferenceType(referenceType);
        DateTime referenceStart;
        DateTime referenceEnd;
        if (referenceType.equalsIgnoreCase("DAY")) {
            referenceStart = start.minusDays(1);
            referenceEnd = end.minusDays(1);
            referenceBag.setDescription("昨天");
        } else if (referenceType.equalsIgnoreCase("WEEK")) {
            referenceStart = start.minusDays(7);
            referenceEnd = end.minusDays(7);
            referenceBag.setDescription("上周");
        } else if (referenceType.equalsIgnoreCase("MONTH")) {
            referenceStart = start.minusMonths(1);
            referenceEnd = end.minusMonths(1);
            referenceBag.setDescription("上月");
        } else {
            throw new IllegalArgumentException("unknown reference_type: " + referenceType);
        }
        MetricData elasticsearchMetric = pullMetricData(referenceStart, referenceEnd, metricContract, ruleSettings);
        Double metricValue = toDouble(elasticsearchMetric.getMetricValue(), 0D);
        Double percentage = calculatePercentage(current, metricValue);
        referenceBag.setValue(metricValue);
        referenceBag.setPercentage(percentage);
        return referenceBag;
    }

    private Double toDouble(Object value, Double defaultValue) {
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception ex) {
            LOGGER.error("error when toDouble, value: " + value.toString(), ex);
            return defaultValue;
        }
    }

    private Double calculatePercentage(Double current, Double reference) {
        if (reference == 0) {
            return (current - reference) * 100 / (reference + 1);
        }
        return (current - reference) * 100 / reference;
    }
}
