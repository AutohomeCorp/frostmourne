package com.autohome.frostmourne.monitor.integration.service.admin.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.service.admin.impl.ScheduleService;
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
public class ScheduleServiceTest {

    @Resource
    private ScheduleService scheduleService;

    @Test
    public void addJobTest() {
        scheduleService.addJob(11L, "13 0/2 * * * ?", AlarmStatus.CLOSE);
    }

    @Test
    public void removeJobTest() {
        scheduleService.removeJob(4);
    }

    @Test
    public void updateJobTest() {
        scheduleService.updateJob(11L, 5, "13 0/3 * * * ?", AlarmStatus.CLOSE);
    }
}
