package com.autohome.frostmourne.monitor.service.core.query;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.IntegrationTest;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.core.query.impl.QueryService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class QueryServiceIntegrationTest extends IntegrationTest {

    @Resource
    private QueryService queryService;

    @Test
    public void ElasticsearchQueryTest() {
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(10);
        ElasticsearchDataResult result = queryService.elasticsearchQuery("dealer.program.log", start.toDate(), end.toDate(),
                "Team: dealer.arch", null, "desc", 60);
    }
}
