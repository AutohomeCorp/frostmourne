package com.autohome.frostmourne.monitor.dao.skywalking.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingDuration;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogs;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingOrder;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingPagination;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingStep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 21:24
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"local"})
@EnabledIf(value = "#{'IntegrationTest'.equals(systemProperties['test-profile'])}")
class SkywalkingDaoIntegrationTest {

    @Resource
    private SkywalkingDao skywalkingDao;

    @Test
    void queryLogsTest() {
        SkywalkingLogQueryCondition condition = new SkywalkingLogQueryCondition();
        condition.setQueryOrder(SkywalkingOrder.DES);

        SkywalkingPagination pagination = new SkywalkingPagination();
        pagination.setNeedTotal(true);
        pagination.setPageNum(1);
        pagination.setPageSize(10);
        condition.setPaging(pagination);

        SkywalkingDuration duration = new SkywalkingDuration();
        duration.setStart("2022-06-08 1701");
        duration.setEnd("2022-06-07 1716");
        duration.setStep(SkywalkingStep.MINUTE);
        condition.setQueryDuration(duration);

        condition.setKeywordsOfContent(Arrays.asList("INFO"));

        SkywalkingLogs logs = skywalkingDao.queryLogs("skywalking", "skywalking", "http://demo.skywalking.apache.org", condition);
    }

}
