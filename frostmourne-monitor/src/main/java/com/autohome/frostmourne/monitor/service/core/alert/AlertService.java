package com.autohome.frostmourne.monitor.service.core.alert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.AlertContract;
import com.autohome.frostmourne.monitor.contract.enums.AlertType;
import com.autohome.frostmourne.monitor.contract.enums.SendStatus;
import com.autohome.frostmourne.monitor.contract.enums.SilenceStatus;
import com.autohome.frostmourne.monitor.contract.enums.VerifyResult;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmLogMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertLogMapper;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements IAlertService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AlertService.class);

    @Resource
    private IFrostmourneSpiApi frostmourneSpiApi;

    @Resource
    private AlertLogMapper alertLogMapper;

    @Resource
    private AlarmLogMapper alarmLogMapper;

    public void alert(AlarmProcessLogger alarmProcessLogger) {
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        List<UserInfo> recipients = recipients(alertContract.getRecipients());
        if (recipients.size() == 0) {
            LOGGER.error("no recipients, alarmId: " + alarmProcessLogger.getAlarmContract().getId());
            return;
        }

        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        AlarmLog latestAlarmLog = alarmLogMapper.selectLatest(alarmId, null);
        if (!alarmProcessLogger.getAlert()) {
            checkRecover(latestAlarmLog, alarmProcessLogger, recipients);
        } else {
            processProblem(alarmProcessLogger, recipients, latestAlarmLog);
        }
    }

    private void sendAlert(AlarmProcessLogger alarmProcessLogger, List<UserInfo> recipients, String alertType) {
        alarmLog(alarmProcessLogger);
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        AlarmMessage alarmMessage = new AlarmMessage();
        String alertContent = null;
        if (alertType.equalsIgnoreCase(AlertType.PROBLEM)) {
            alarmMessage.setContent(String.format("消息类型: [问题] %s分钟内持续报警将不重复发送\n%s",
                    alertContract.getSilence(), alarmProcessLogger.getAlertMessage()));
            alertContent = alarmProcessLogger.getAlertMessage();
        } else if (alertType.equalsIgnoreCase(AlertType.RECOVER)) {
            AlertLog alertLog = this.alertLogMapper.selectLatest(alarmProcessLogger.getAlarmContract().getId(), AlertType.PROBLEM, SilenceStatus.NO);
            alertContent = "消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n" + alertLog.getContent();
            alarmMessage.setContent(alertContent);
        }
        alarmMessage.setTitle(String.format("[霜之哀伤监控平台][id:%s]%s", alarmProcessLogger.getAlarmContract().getId(), alarmProcessLogger.getAlarmContract().getAlarm_name()));
        alarmMessage.setRecipients(recipients);
        alarmMessage.setWays(alertContract.getWays());
        alarmMessage.setDingHook(alertContract.getDing_robot_hook());
        alarmMessage.setHttpPostEndpoint(alertContract.getHttp_post_url());
        alarmMessage.setWechatHook(alertContract.getWechat_robot_hook());
        Protocol<List<MessageResult>> protocol = frostmourneSpiApi.send(alarmMessage, "frostmourne-monitor");
        if (protocol.getReturncode() != 0) {
            LOGGER.error("error when send alert. protocol: " + JacksonUtil.serialize(protocol));
            return;
        }
        saveAlertLog(alertType, SilenceStatus.NO, protocol.getResult(), recipients, alarmProcessLogger.getAlarmContract().getId(),
                alertContent, alarmProcessLogger.getAlarmLog().getId());
    }

    private List<UserInfo> recipients(List<String> accounts) {
        List<UserInfo> recipients = new ArrayList<>();
        for (String userName : accounts) {
            Protocol<UserInfo> protocol = frostmourneSpiApi.findByAccount("frostmourne-monitor", userName);
            if (protocol.getReturncode() == 0) {
                recipients.add(protocol.getResult());
            } else {
                LOGGER.error(String.format("error when frostmourneSpiApi.findByAccount, account: %s, response: %s", userName, JacksonUtil.serialize(protocol)));
            }
        }
        return recipients;
    }

    private void saveAlertLog(String alertType, String silenceStatus, List<MessageResult> messageResults,
                              List<UserInfo> userInfos, Long alarmId, String content, Long executeId) {
        for (MessageResult messageResult : messageResults) {
            for (UserInfo userInfo : userInfos) {
                AlertLog alertLog = new AlertLog();
                alertLog.setAlarm_id(alarmId);
                alertLog.setContent(content);
                alertLog.setExecute_id(executeId);
                alertLog.setCreate_at(new Date());
                alertLog.setRecipient(userInfo.getAccount());
                alertLog.setIn_silence(SilenceStatus.NO);
                alertLog.setSend_status(messageResult.getSuccess() == 1 ? SendStatus.SUCCESS : SendStatus.FAIL);
                alertLog.setWay(messageResult.getWay());
                alertLog.setAlert_type(alertType);
                alertLog.setIn_silence(silenceStatus);
                alertLogMapper.insert(alertLog);
            }
        }
    }

    private void checkRecover(AlarmLog latestAlarmLog, AlarmProcessLogger alarmProcessLogger, List<UserInfo> recipients) {
        //if not alert, check if send recover message
        if (latestAlarmLog != null && latestAlarmLog.getVerify_result().equalsIgnoreCase(VerifyResult.TRUE)) {
            //this is recover message
            sendAlert(alarmProcessLogger, recipients, AlertType.RECOVER);
        } else {
            alarmLog(alarmProcessLogger);
        }
    }

    private boolean checkSilence(AlarmProcessLogger alarmProcessLogger) {
        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        AlarmLog latestNoProblemAlarmLog = alarmLogMapper.selectLatest(alarmId, VerifyResult.FALSE);
        Long current = System.currentTimeMillis();
        Long silenceThreshold = alarmProcessLogger.getAlarmContract().getAlertContract().getSilence() * 60 * 1000;
        if (latestNoProblemAlarmLog != null && current - latestNoProblemAlarmLog.getCreate_at().getTime() < silenceThreshold) {
            return true;
        }
        //find latest problem and not silence alert time
        AlertLog latestAlertLog = alertLogMapper.selectLatest(alarmId, AlertType.PROBLEM, SilenceStatus.NO);
        if (latestAlertLog != null && current - latestAlertLog.getCreate_at().getTime() < silenceThreshold) {
            return true;
        }

        return false;
    }

    private void saveSilenceAlertLog(AlarmProcessLogger alarmProcessLogger, List<UserInfo> recipients) {
        alarmLog(alarmProcessLogger);
        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        for (String way : alertContract.getWays()) {
            for (UserInfo userInfo : recipients) {
                AlertLog alertLog = new AlertLog();
                alertLog.setAlarm_id(alarmId);
                alertLog.setContent(alarmProcessLogger.getAlertMessage());
                alertLog.setExecute_id(alarmProcessLogger.getAlarmLog().getId());
                alertLog.setCreate_at(new Date());
                alertLog.setRecipient(userInfo.getAccount());
                alertLog.setSend_status(SendStatus.NONE);
                alertLog.setWay(way);
                alertLog.setAlert_type(AlertType.PROBLEM);
                alertLog.setIn_silence(SilenceStatus.YES);
                alertLogMapper.insert(alertLog);
            }
        }
    }

    private void processProblem(AlarmProcessLogger alarmProcessLogger, List<UserInfo> recipients, AlarmLog latestAlarmLog) {
        if (latestAlarmLog == null || latestAlarmLog.getVerify_result().equalsIgnoreCase(VerifyResult.FALSE)) {
            sendAlert(alarmProcessLogger, recipients, AlertType.PROBLEM);
            return;
        }
        boolean inSilence = checkSilence(alarmProcessLogger);
        if (inSilence) {
            saveSilenceAlertLog(alarmProcessLogger, recipients);
        } else {
            sendAlert(alarmProcessLogger, recipients, AlertType.PROBLEM);
        }
    }

    @Override
    public void alarmLog(AlarmProcessLogger alarmProcessLogger) {
        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setAlarm_id(alarmProcessLogger.getAlarmContract().getId());
        alarmLog.setCost((int) (alarmProcessLogger.getEnd().getMillis() - alarmProcessLogger.getStart().getMillis()));
        alarmLog.setCreate_at(new Date());
        alarmLog.setExe_start(alarmProcessLogger.getStart().toDate());
        alarmLog.setExe_end(alarmProcessLogger.getEnd().toDate());
        alarmLog.setExecute_result(alarmProcessLogger.getExecuteStatus().getName());
        alarmLog.setMessage(alarmProcessLogger.traceInfo());
        alarmLog.setVerify_result(alarmProcessLogger.getAlert() ? VerifyResult.TRUE : VerifyResult.FALSE);
        alarmLogMapper.insert(alarmLog);
        alarmProcessLogger.setAlarmLog(alarmLog);
    }
}
