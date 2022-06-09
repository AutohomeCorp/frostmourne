package com.autohome.frostmourne.monitor.service.core.metric;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
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
class MetricServiceIntegrationTest {

    @Resource
    private MetricService metricService;

    @Test
    void findMetricTest() {
        IMetric result1 = metricService.findMetric(DataSourceType.elasticsearch, "numeric");
        assertNotNull(result1);

        IMetric result2 = metricService.findMetric(DataSourceType.elasticsearch, "bucket_numeric");
        assertNotNull(result2);

        IMetric result3 = metricService.findMetric(DataSourceType.elasticsearch, "ring_compare");
        assertNotNull(result3);

        IMetric result4 = metricService.findMetric(DataSourceType.elasticsearch, "same_time");
        assertNotNull(result4);

        IMetric result5 = metricService.findMetric(DataSourceType.http, "object");
        assertNotNull(result5);


    }


}
