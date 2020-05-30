package com.autohome.frostmourne.monitor.integration.service.core.query;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.core.query.QueryService;
import org.joda.time.DateTime;
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
public class QueryServiceTest {

    @Resource
    private QueryService queryService;

    @Test
    public void ElasticsearchQueryTest() {
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(10);
        ElasticsearchDataResult result = queryService.elasticsearchQuery("dealer.program.log", start.toDate(), end.toDate(), "Team: dealer.arch",
                null, "desc", 60);
    }
}
