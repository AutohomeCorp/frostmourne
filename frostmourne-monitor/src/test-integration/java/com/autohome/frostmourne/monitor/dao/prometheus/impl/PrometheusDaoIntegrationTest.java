package com.autohome.frostmourne.monitor.dao.prometheus.impl;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValues;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.RangeQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"local"})
@EnabledIf(value = "#{'IntegrationTest'.equals(systemProperties['test-profile'])}")
class PrometheusDaoIntegrationTest {

    @Resource
    private PrometheusDao prometheusDao;

    @Test
    void queryTest() {
        PrometheusResponse<MetricValue> result = prometheusDao.query("", "", "http://demo.do.prometheus.io:9090/", "access_evaluation_duration_count");
    }

    @Test
    void queryRangeTest() {
        RangeQuery rangeQuery = new RangeQuery();
        rangeQuery.setQuery("access_evaluation_duration_count");
        rangeQuery.setStart("now-1d");
        rangeQuery.setEnd("now");
        rangeQuery.setStep("1h");
        PrometheusResponse<MetricValues> result = prometheusDao.queryRange("", "", "http://demo.do.prometheus.io:9090/", JacksonUtil.serialize(rangeQuery));
    }
}
