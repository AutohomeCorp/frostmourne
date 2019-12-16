package com.autohome.frostmourne.monitor.integration.service.core;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.execute.AlarmService;
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
public class AlarmServiceTest {

    @Resource
    private AlarmService alarmService;

    @Test
    public void runTest() {
        alarmService.run("kechangqing", 13L, false);
    }
}
