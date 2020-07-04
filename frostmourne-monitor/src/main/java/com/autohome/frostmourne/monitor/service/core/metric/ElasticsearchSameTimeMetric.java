package com.autohome.frostmourne.monitor.service.core.metric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.ElasticsearchMetric;
import com.autohome.frostmourne.monitor.service.core.domain.ReferenceBag;
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
        try {
            ElasticsearchMetric elasticsearchMetric = elasticsearchDataQuery.queryElasticsearchMetricValue(start, end, metricContract);
            resultMap.put("CURRENT", toDouble(elasticsearchMetric.getMetricValue(), 0D));
        } catch (IOException ex) {
            throw new RuntimeException("error when queryElasticsearchMetricValue", ex);
        }
        List<String> referenceTypeList = findReferenceTypeList(settings);
        List<ReferenceBag> referenceDataList = new ArrayList<>();
        try {
            for (String referenceType : referenceTypeList) {
                ReferenceBag referenceBag = calculateReference(start, end, referenceType, metricContract);
                referenceDataList.add(referenceBag);
            }

            resultMap.put("REFERENCE_LIST", referenceDataList);
        } catch (IOException ex) {
            throw new RuntimeException("error when calculateReference", ex);
        }

        return resultMap;
    }

    private ReferenceBag calculateReference(DateTime start, DateTime end, String referenceType,
                                            MetricContract metricContract) throws IOException {
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
        ElasticsearchMetric elasticsearchMetric = this.elasticsearchDataQuery.queryElasticsearchMetricValue(referenceStart, referenceEnd, metricContract);
        referenceBag.setValue(toDouble(elasticsearchMetric.getMetricValue(), 0D));
        return new ReferenceBag();
    }

    private Double toDouble(Object value, Double defaultValue) {
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception ex) {
            LOGGER.error("error when toDouble, value: " + value.toString(), ex);
            return defaultValue;
        }
    }
}
