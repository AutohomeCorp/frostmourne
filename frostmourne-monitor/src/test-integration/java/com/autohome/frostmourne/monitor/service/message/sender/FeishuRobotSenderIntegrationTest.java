package com.autohome.frostmourne.monitor.service.message.sender;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
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
class FeishuRobotSenderIntegrationTest {

    @Resource
    private FeishuRobotSender feishuRobotSender;

    @Test
    void sendTest() {
        AlarmMessageBO alarmMessageBO = new AlarmMessageBO();
        alarmMessageBO.setTitle("[霜之哀伤监控平台]");
        alarmMessageBO.setContent("ccccccccccccccccccccccc");
        alarmMessageBO.setFeiShuHook("hook");
        feishuRobotSender.send(alarmMessageBO);
    }

}
