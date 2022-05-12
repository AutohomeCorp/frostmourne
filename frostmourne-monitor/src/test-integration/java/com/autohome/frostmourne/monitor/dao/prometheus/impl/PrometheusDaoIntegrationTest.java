package com.autohome.frostmourne.monitor.dao.prometheus.impl;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
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
}
