package com.autohome.frostmourne.monitor.service.core.query.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarmQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarms;
import com.autohome.frostmourne.monitor.service.core.query.ISkywalkingDataQuery;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.skywalking.ISkywalkingDao;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingDuration;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogs;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingPagination;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingStep;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 23:09
 */

@Service
public class SkywalkingDataQuery implements ISkywalkingDataQuery {

    @Resource
    private ISkywalkingDao skywalkingDao;

    @Override
    public MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract) {
        String skywalkingDataCategory = metricContract.getDataNameContract().getSettings().get("skywalkingDataCategory");
        String user = metricContract.getDataSourceContract().getSettings().get("username");
        String password = metricContract.getDataSourceContract().getSettings().get("password");
        MetricData metricData = new MetricData();
        if (skywalkingDataCategory.equalsIgnoreCase("logging")) {
            SkywalkingLogQueryCondition skywalkingLogQueryCondition = new SkywalkingLogQueryCondition();
            SkywalkingLogQueryCondition inputCondition = JacksonUtil.deSerialize(metricContract.getQueryString(), SkywalkingLogQueryCondition.class);
            skywalkingLogQueryCondition.setServiceId(inputCondition.getServiceId());
            skywalkingLogQueryCondition.setExcludingKeywordsOfContent(inputCondition.getExcludingKeywordsOfContent());
            skywalkingLogQueryCondition.setKeywordsOfContent(inputCondition.getKeywordsOfContent());
            skywalkingLogQueryCondition.setEndpointId(inputCondition.getEndpointId());
            skywalkingLogQueryCondition.setTags(inputCondition.getTags());
            skywalkingLogQueryCondition.setServiceInstanceId(inputCondition.getServiceInstanceId());
            skywalkingLogQueryCondition.setTraceScopeCondition(inputCondition.getTraceScopeCondition());
            skywalkingLogQueryCondition.setQueryDuration(duration(start, end, SkywalkingStep.SECOND));
            SkywalkingPagination pagination = new SkywalkingPagination();
            pagination.setNeedTotal(true);
            pagination.setPageNum(1);
            pagination.setPageSize(5);
            skywalkingLogQueryCondition.setPaging(pagination);
            SkywalkingLogs skywalkingLogs =
                skywalkingDao.queryLogs(user, password, metricContract.getDataSourceContract().getServiceAddress(), skywalkingLogQueryCondition);
            metricData.setMetricValue(skywalkingLogs.getTotal());
            if (skywalkingLogs.getTotal() > 0) {
                Map<String, Object> dataMap = JacksonUtil.mapper().convertValue(skywalkingLogs.getLogs().get(0), Map.class);
                metricData.setLatestDocument(dataMap);
            }
            return metricData;
        } else if (skywalkingDataCategory.equalsIgnoreCase("alarms")) {
            SkywalkingAlarmQueryCondition skywalkingAlarmQueryCondition = new SkywalkingAlarmQueryCondition();
            SkywalkingPagination pagination = new SkywalkingPagination();
            pagination.setNeedTotal(true);
            pagination.setPageNum(1);
            pagination.setPageSize(10);
            skywalkingAlarmQueryCondition.setPaging(pagination);

            skywalkingAlarmQueryCondition.setDuration(duration(start, end, SkywalkingStep.MINUTE));

            SkywalkingAlarmQueryCondition inputCondition = JacksonUtil.deSerialize(metricContract.getQueryString(), SkywalkingAlarmQueryCondition.class);
            skywalkingAlarmQueryCondition.setKeyword(inputCondition.getKeyword());
            skywalkingAlarmQueryCondition.setScope(inputCondition.getScope());
            skywalkingAlarmQueryCondition.setTags(inputCondition.getTags());

            SkywalkingAlarms skywalkingAlarms =
                skywalkingDao.queryAlarms(user, password, metricContract.getDataSourceContract().getServiceAddress(), skywalkingAlarmQueryCondition);
            metricData.setMetricValue(skywalkingAlarms.getTotal());
            if (skywalkingAlarms.getTotal() > 0) {
                Map<String, Object> dataMap = JacksonUtil.mapper().convertValue(skywalkingAlarms.getItems().get(0), Map.class);
                metricData.setLatestDocument(dataMap);
            }
            Map<String, Object> context = new HashMap<>();
            context.put("TopNData", skywalkingAlarms.getItems());
            metricData.setContext(context);
            return metricData;
        }

        throw new IllegalArgumentException("not supported skywalking data category: " + skywalkingDataCategory);
    }

    private SkywalkingDuration duration(DateTime start, DateTime end, String step) {
        SkywalkingDuration duration = new SkywalkingDuration();
        duration.setStep(step);
        String format = null;
        if (step.equalsIgnoreCase(SkywalkingStep.SECOND)) {
            format = "yyyy-MM-dd HHmmss";
        } else if (step.equalsIgnoreCase(SkywalkingStep.MINUTE)) {
            format = "yyyy-MM-dd HHmm";
        } else if (step.equalsIgnoreCase(SkywalkingStep.HOUR)) {
            format = "yyyy-MM-dd HH";
        } else if (step.equalsIgnoreCase(SkywalkingStep.DAY)) {
            format = "yyyy-MM-dd";
        } else {
            throw new IllegalArgumentException("unknown step: " + step);
        }
        duration.setStart(start.toDateTime(DateTimeZone.UTC).toString(format));
        duration.setEnd(end.toDateTime(DateTimeZone.UTC).toString(format));
        return duration;
    }
}
