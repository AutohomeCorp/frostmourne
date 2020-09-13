package com.autohome.frostmourne.monitor.service.core.metric.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.domain.ReferenceBag;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.query.IElasticsearchDataQuery;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchSameTimeMetric extends AbstractSameTimeMetric {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchSameTimeMetric.class);

    @Resource
    private IElasticsearchDataQuery elasticsearchDataQuery;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        Map<String, Object> resultMap = new HashMap<>();
        String periodUnit = findPeriodUnit(settings);
        DateTime now = DateTime.now();
        DateTime end = findEnd(now, periodUnit);
        DateTime start = findStart(end, periodUnit);
        resultMap.put("startTime", start.toDateTimeISO().toString());
        resultMap.put("endTime", end.toDateTimeISO().toString());
        resultMap.put("PERIOD_UNIT_DESCRIPTION", findPeriodUnitDescription(periodUnit));
        Double current = null;
        try {
            MetricData elasticsearchMetric = elasticsearchDataQuery.queryElasticsearchMetricValue(start, end, metricContract);
            current = toDouble(elasticsearchMetric.getMetricValue(), 0D);
            resultMap.put("CURRENT", current);
        } catch (IOException ex) {
            throw new RuntimeException("error when queryElasticsearchMetricValue", ex);
        }
        List<String> referenceTypeList = findReferenceTypeList(settings);
        List<ReferenceBag> referenceDataList = new ArrayList<>();
        try {
            for (String referenceType : referenceTypeList) {
                ReferenceBag referenceBag = calculateReference(start, end, referenceType, metricContract, current);
                referenceDataList.add(referenceBag);
            }
            resultMap.put("REFERENCE_LIST", referenceDataList);
        } catch (IOException ex) {
            throw new RuntimeException("error when calculateReference", ex);
        }

        return resultMap;
    }

    private ReferenceBag calculateReference(DateTime start, DateTime end, String referenceType,
                                            MetricContract metricContract, Double current) throws IOException {
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
        MetricData elasticsearchMetric = this.elasticsearchDataQuery.queryElasticsearchMetricValue(referenceStart, referenceEnd, metricContract);
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
