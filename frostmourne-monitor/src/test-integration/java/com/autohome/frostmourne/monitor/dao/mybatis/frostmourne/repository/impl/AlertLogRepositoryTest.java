package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.IntegrationTest;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

class AlertLogRepositoryTest extends IntegrationTest {

    @Resource
    private AlertLogRepository alertLogRepository;

    @Test
    void findTest() {
        List<AlertLog> alertLogs = alertLogRepository.find(DateTime.parse("2022-04-18T16:00:00.000Z").toDate(),
            DateTime.parse("2022-04-19T15:59:59.999Z").toDate(), null, null, null, null, null, null, null);
    }
}
