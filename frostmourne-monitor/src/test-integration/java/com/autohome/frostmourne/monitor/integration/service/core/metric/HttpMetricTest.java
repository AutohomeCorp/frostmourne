package com.autohome.frostmourne.monitor.integration.service.core.metric;

import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.metric.HttpMetric;
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
public class HttpMetricTest {

    @Resource
    private HttpMetric httpMetric;

    @Test
    public void pullMetricTest() {
        MetricContract metricContract = new MetricContract();
        metricContract.setQuery_string("http://localhost:9200/_cluster/health?pretty");
        Map<String, Object> result = httpMetric.pullMetric(metricContract, null);
    }

    @Test
    public void pullMetricTest_with_post_data() {
        MetricContract metricContract = new MetricContract();
        metricContract.setQuery_string("http://localhost:9200/_cluster/health?pretty");
        Map<String, Object> result = httpMetric.pullMetric(metricContract, null);
    }
}
