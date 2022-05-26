package com.autohome.frostmourne.monitor.service.core.alert;

import java.time.LocalDateTime;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertEvent;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.ConfigMap;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IConfigMapRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl.AlertEventRepository;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.contract.AlertContract;
import com.autohome.frostmourne.monitor.model.contract.AlertUpgradeContract;
import com.autohome.frostmourne.monitor.model.contract.ServiceInfoSimpleContract;
import com.autohome.frostmourne.monitor.model.enums.*;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.core.domain.ConfigMapKeys;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.message.MessageService;
import com.autohome.frostmourne.monitor.tool.ExpressionParser;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class AlertService implements IAlertService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AlertService.class);

    @Value("${frostmourne.message.title}")
    private String messageTitle;

    @Resource
    private MessageService messageService;

    @Resource
    private IAlertLogRepository alertLogRepository;

    @Resource
    private IAccountService accountService;

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Resource
    private IConfigMapRepository configMapRepository;

    @Resource
    private AlertEventRepository alertEventRepository;

    @Override
    public void alert(AlarmProcessLogger alarmProcessLogger) {
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        List<AccountInfo> recipients = recipients(alertContract.getRecipients(), alarmProcessLogger.getAlarmContract().getServiceInfo());
        if (recipients.size() == 0) {
            LOGGER.error("no recipients, alarmId: " + alarmProcessLogger.getAlarmContract().getId());
            return;
        }

        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        Optional<AlarmLog> latestAlarmLog = alarmLogRepository.selectLatest(alarmId, null);
        if (!alarmProcessLogger.getAlert()) {
            checkRecover(latestAlarmLog, alarmProcessLogger, recipients);
        } else {
            processProblem(alarmProcessLogger, recipients, latestAlarmLog);
        }
    }

    private void sendAlert(AlarmProcessLogger alarmProcessLogger, List<AccountInfo> recipients, AlertType alertType) {
        alarmLog(alarmProcessLogger);

        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        AlarmMessageBO alarmMessageBO = new AlarmMessageBO();
        String alertContent = null;
        if (AlertTemplateType.MARKDOWN.equals(alarmProcessLogger.getAlarmContract().getRuleContract().getAlertTemplateType())) {
            alertContent = alarmProcessLogger.getAlertMessage();
            if (AlertType.RECOVER.equals(alertType)) {
                AlertLog alertLog =
                    this.alertLogRepository.selectLatest(alarmProcessLogger.getAlarmContract().getId(), AlertType.PROBLEM, SilenceStatus.NO).get();
                alertContent = "### [恢复] 请自己检查问题是否解决，上次报警内容如下\n" + alertLog.getContent();
            }
            alarmMessageBO.setContent(alertContent);
        } else {
            String risk = "";
            if (alarmProcessLogger.getAlarmContract().getRiskLevel() != null) {
                risk = "[" + alarmProcessLogger.getAlarmContract().getRiskLevel().desc() + "] ";
            }
            String timeString = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
            if (AlertType.PROBLEM.equals(alertType)) {
                alertContent = alarmProcessLogger.getAlertMessage();
                alarmMessageBO.setContent(String.format("[%s] [问题] %s\n%s", timeString, risk, alarmProcessLogger.getAlertMessage()));
            } else if (AlertType.RECOVER.equals(alertType)) {
                AlertLog alertLog =
                    this.alertLogRepository.selectLatest(alarmProcessLogger.getAlarmContract().getId(), AlertType.PROBLEM, SilenceStatus.NO).get();
                alertContent = String.format("[%s] [恢复] %s请自己检查问题是否解决，上次报警内容如下\n%s", timeString, risk, alertLog.getContent());
                alarmMessageBO.setContent(alertContent);
            }
        }

        alarmMessageBO.setAlertTemplateType(alarmProcessLogger.getAlarmContract().getRuleContract().getAlertTemplateType());
        alarmMessageBO.setTitle(String.format("[%s][id:%s]%s", Strings.isNullOrEmpty(messageTitle) ? alertTitle() : messageTitle,
            alarmProcessLogger.getAlarmContract().getId(), alarmProcessLogger.getAlarmContract().getAlarmName()));
        alarmMessageBO.setRecipients(recipients);
        alarmMessageBO.setWays(generateWays(alertContract.getWays(), alertContract.getWechatRobotHook(), alertContract.getDingRobotHook()));
        alarmMessageBO.setDingHook(alertContract.getDingRobotHook());
        alarmMessageBO.setHttpPostEndpoint(alertContract.getHttpPostUrl());
        alarmMessageBO.setWechatHook(alertContract.getWechatRobotHook());
        alarmMessageBO.setFeiShuHook(alertContract.getFeishuRobotHook());
        alarmMessageBO.setContext(alarmProcessLogger.getContext());

        messageService.send(alarmMessageBO);
        saveAlertLog(alertType, alarmMessageBO.getResultList(), recipients, alarmProcessLogger.getAlarmContract().getId(), alertContent,
            alarmProcessLogger.getAlarmLog().getId());

        // alert upgrade
        AlertUpgradeContract alertUpgradeContract = alarmProcessLogger.getAlarmContract().getAlertUpgradeContract();
        boolean upgrade = judgeAlertUpgrade(alertType, alertUpgradeContract);
        if (upgrade) {
            List<AccountInfo> alertUpgradeAccountInfo =
                recipients(alertUpgradeContract.getRecipients(), alarmProcessLogger.getAlarmContract().getServiceInfo());
            if (alertUpgradeAccountInfo.size() == 0) {
                LOGGER.error("no upgrade recipients, alarmId: " + alarmProcessLogger.getAlarmContract().getId());
            }
            alarmMessageBO.setRecipients(alertUpgradeAccountInfo);
            alarmMessageBO
                .setWays(generateWays(alertUpgradeContract.getWays(), alertUpgradeContract.getWechatRobotHook(), alertUpgradeContract.getDingRobotHook()));
            alarmMessageBO.setDingHook(alertUpgradeContract.getDingRobotHook());
            alarmMessageBO.setHttpPostEndpoint(alertUpgradeContract.getHttpPostUrl());
            alarmMessageBO.setWechatHook(alertUpgradeContract.getWechatRobotHook());
            alarmMessageBO.setFeiShuHook(alertUpgradeContract.getFeishuRobotHook());
            messageService.send(alarmMessageBO);
            saveAlertLog(AlertType.UPGRADE, alarmMessageBO.getResultList(), alertUpgradeAccountInfo, alarmProcessLogger.getAlarmContract().getId(),
                alertContent, alarmProcessLogger.getAlarmLog().getId());
        }

        // save alert event
        saveAlertEvent(alarmProcessLogger, alertType, false, upgrade);
    }

    private boolean judgeAlertUpgrade(AlertType alertType, AlertUpgradeContract alertUpgradeContract) {
        if (!AlertType.PROBLEM.equals(alertType)) {
            return false;
        }
        if (alertUpgradeContract == null || AlarmUpgradeStatus.CLOSE.equals(alertUpgradeContract.getStatus())) {
            return false;
        }
        List<AlarmLog> alarmLogList = alarmLogRepository.selectRecently(alertUpgradeContract.getTimesToUpgrade());
        return alarmLogList.size() == alertUpgradeContract.getTimesToUpgrade() && alarmLogList.stream().allMatch(AlarmLog::getAlert);
    }

    private void saveAlertEvent(AlarmProcessLogger alarmProcessLogger, AlertType alertType, boolean inSilence, boolean alertUpgrade) {
        AlertEvent alertEvent = new AlertEvent();
        alertEvent.setAlarmId(alarmProcessLogger.getAlarmContract().getId());
        alertEvent.setAlertType(alertType);
        alertEvent.setInSilence(inSilence);
        alertEvent.setEventMd5(JacksonUtil.serialize(alarmProcessLogger.getEventMd5()));
        alertEvent.setUpgrade(alertUpgrade);
        alertEvent.setCreateAt(LocalDateTime.now());
        alertEventRepository.insert(alertEvent);
    }

    private List<MessageWay> generateWays(List<String> ways, String wechatRobotHook, String dingRobotHook) {
        List<MessageWay> messageWays = new ArrayList<>();
        for (String way : ways) {
            if ("email".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.EMAIL);
            }
            if ("sms".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.SMS);
            }
            if ("wechat".equalsIgnoreCase(way)) {
                if (StringUtils.isBlank(wechatRobotHook)) {
                    messageWays.add(MessageWay.WECHAT);
                } else {
                    messageWays.add(MessageWay.WECHAT_ROBOT);
                }
            }
            if ("dingding".equalsIgnoreCase(way)) {
                if (StringUtils.isBlank(dingRobotHook)) {
                    messageWays.add(MessageWay.DING_DING);
                } else {
                    messageWays.add(MessageWay.DING_ROBOT);
                }
            }
            if ("feishu".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.FEI_SHU_ROBOT);
            }
            if ("http_post".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.HTTP_POST);
            }
        }
        if (messageWays.isEmpty()) {
            throw new IllegalArgumentException("require at least one message way");
        }
        return messageWays;
    }

    private List<AccountInfo> recipients(List<String> accounts, ServiceInfoSimpleContract serviceInfoSimpleContract) {
        List<AccountInfo> recipients = new ArrayList<>();
        if (serviceInfoSimpleContract != null && !accounts.contains(serviceInfoSimpleContract.getOwner())) {
            accounts.add(serviceInfoSimpleContract.getOwner());
        }
        for (String userName : accounts) {
            Optional<AccountInfo> accountInfo = accountService.findByAccount(userName);
            accountInfo.ifPresent(recipients::add);
        }
        return recipients;
    }

    private void saveAlertLog(AlertType alertType, List<MessageResult> messageResults, List<AccountInfo> accountInfos, Long alarmId, String content,
        Long executeId) {
        for (MessageResult messageResult : messageResults) {
            for (AccountInfo accountInfo : accountInfos) {
                AlertLog alertLog = new AlertLog();
                alertLog.setAlarmId(alarmId);
                alertLog.setContent(content);
                alertLog.setExecuteId(executeId);
                alertLog.setCreateAt(new Date());
                alertLog.setRecipient(accountInfo.getAccount());
                alertLog.setSendStatus(messageResult.getSuccess() ? SendStatus.SUCCESS : SendStatus.FAIL);
                alertLog.setWay(messageResult.getWay().name());
                alertLog.setAlertType(alertType);
                alertLog.setInSilence(SilenceStatus.NO);
                alertLogRepository.insert(alertLog);
            }
        }
    }

    private void checkRecover(Optional<AlarmLog> latestAlarmLog, AlarmProcessLogger alarmProcessLogger, List<AccountInfo> recipients) {
        // if not alert, check if send recover message
        if (needRecover(latestAlarmLog, alarmProcessLogger)) {
            sendAlert(alarmProcessLogger, recipients, AlertType.RECOVER);
        } else {
            alarmLog(alarmProcessLogger);
        }
    }

    private boolean needRecover(Optional<AlarmLog> latestAlarmLog, AlarmProcessLogger alarmProcessLogger) {
        // if not alert, check if send recover message
        if (latestAlarmLog.isPresent() && latestAlarmLog.get().getVerifyResult().equals(VerifyResult.TRUE)) {
            // this is recover message
            return RecoverNoticeStatus.OPEN.equals(alarmProcessLogger.getAlarmContract().getRecoverNoticeStatus());
        }
        return false;
    }

    private boolean checkSilence(AlarmProcessLogger alarmProcessLogger) {
        Long silence = alarmProcessLogger.getAlarmContract().getAlertContract().getSilence();
        if (silence == 0L) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        // query all alert_event where create at now-silence to now
        List<AlertEvent> alertEventList =
            alertEventRepository.selectAllByTime(alarmProcessLogger.getAlarmContract().getId(), false, AlertType.PROBLEM, now.minusMinutes(silence), now);

        if (StringUtils.isBlank(alarmProcessLogger.getAlarmContract().getAlertContract().getSilenceExpression())) {
            return !CollectionUtils.isEmpty(alertEventList);
        } else {
            // if any equals false,
            Optional<AlertEvent> optional = alertEventList.stream().filter(alertEvent -> {
                // if event md5 is blank，should not silence
                if (StringUtils.isBlank(alertEvent.getEventMd5())) {
                    return false;
                }
                Map<String, String> oldEventMd5 = JacksonUtil.deSerialize(alertEvent.getEventMd5(), new TypeReference<HashMap<String, String>>() {});
                return ExpressionParser.calculate(alarmProcessLogger.getAlarmContract().getAlertContract().getSilenceExpression(),
                    alarmProcessLogger.getEventMd5(), oldEventMd5);
            }).findAny();
            return optional.isPresent();
        }
    }

    private void saveSilenceAlertLog(AlarmProcessLogger alarmProcessLogger, List<AccountInfo> recipients) {
        alarmLog(alarmProcessLogger);
        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        for (String way : alertContract.getWays()) {
            for (AccountInfo accountInfo : recipients) {
                AlertLog alertLog = new AlertLog();
                alertLog.setAlarmId(alarmId);
                alertLog.setContent(alarmProcessLogger.getAlertMessage());
                alertLog.setExecuteId(alarmProcessLogger.getAlarmLog().getId());
                alertLog.setCreateAt(new Date());
                alertLog.setRecipient(accountInfo.getAccount());
                alertLog.setSendStatus(SendStatus.NONE);
                alertLog.setWay(way);
                alertLog.setAlertType(AlertType.PROBLEM);
                alertLog.setInSilence(SilenceStatus.YES);
                alertLogRepository.insert(alertLog);
            }
        }
        // save alert event
        saveAlertEvent(alarmProcessLogger, AlertType.PROBLEM, true, false);
    }

    private void processProblem(AlarmProcessLogger alarmProcessLogger, List<AccountInfo> recipients, Optional<AlarmLog> latestAlarmLog) {
        if (!latestAlarmLog.isPresent()) {
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
        alarmLog.setAlarmId(alarmProcessLogger.getAlarmContract().getId());
        alarmLog.setCost((int)(alarmProcessLogger.getEnd().getMillis() - alarmProcessLogger.getStart().getMillis()));
        alarmLog.setCreateAt(new Date());
        alarmLog.setExeStart(alarmProcessLogger.getStart().toDate());
        alarmLog.setExeEnd(alarmProcessLogger.getEnd().toDate());
        alarmLog.setExecuteResult(alarmProcessLogger.getExecuteStatus());
        alarmLog.setVerifyResult(alarmProcessLogger.getVerifyResult() != null ? alarmProcessLogger.getVerifyResult() : VerifyResult.FALSE);
        alarmLog.setAlert(alarmProcessLogger.getAlert());
        alarmLog.setMessage(alarmProcessLogger.traceInfo());
        alarmLogRepository.insert(alarmLog);
        alarmProcessLogger.setAlarmLog(alarmLog);
    }

    private String alertTitle() {
        Optional<ConfigMap> configMapOptional = configMapRepository.find(ConfigMapKeys.ALERT_TITLE);
        if (configMapOptional.isPresent()) {
            return configMapOptional.get().getConfigValue();
        }
        return "霜之哀伤监控平台";
    }
}
