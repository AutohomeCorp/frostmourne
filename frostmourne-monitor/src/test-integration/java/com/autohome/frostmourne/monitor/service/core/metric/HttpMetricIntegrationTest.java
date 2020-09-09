package com.autohome.frostmourne.monitor.service.core.metric;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
@SpringBootTest
@IfProfileValue(name = "test-profile", value = "IntegrationTest")
class HttpMetricIntegrationTest {

    @Resource
    private HttpMetric httpMetric;

    @Test
    void pullMetric() {
        MetricContract metricContract = new MetricContract();
        metricContract.setQueryString("http://10.27.240.152:8080/api/v1/query_range?query=sum(elasticsearch_cluster_health_active_primary_shards%7Bcluster%3D%22lf-dealer-log1%22%7D)&start=1595561616.517&end=1597980816.517&step=9676&_=1597980745490");
        httpMetric.pullMetric(metricContract, new HashMap<>());
    }
}