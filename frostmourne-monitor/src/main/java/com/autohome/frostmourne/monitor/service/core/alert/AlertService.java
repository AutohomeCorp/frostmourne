package com.autohome.frostmourne.monitor.service.core.alert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.AlertContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSimpleContract;
import com.autohome.frostmourne.monitor.contract.enums.AlertType;
import com.autohome.frostmourne.monitor.contract.enums.RecoverNoticeStatus;
import com.autohome.frostmourne.monitor.contract.enums.SendStatus;
import com.autohome.frostmourne.monitor.contract.enums.SilenceStatus;
import com.autohome.frostmourne.monitor.contract.enums.VerifyResult;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ConfigMap;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IConfigMapRepository;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.core.domain.ConfigMapKeys;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.message.MessageService;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    private void sendAlert(AlarmProcessLogger alarmProcessLogger, List<AccountInfo> recipients, String alertType) {
        alarmLog(alarmProcessLogger);
        AlertContract alertContract = alarmProcessLogger.getAlarmContract().getAlertContract();
        AlarmMessageBO alarmMessageBO = new AlarmMessageBO();
        String alertContent = null;
        if (alertType.equalsIgnoreCase(AlertType.PROBLEM)) {
            alarmMessageBO.setContent(String.format("消息类型: [问题] %s分钟内连续报警将不重复发送\n%s",
                    alertContract.getSilence(), alarmProcessLogger.getAlertMessage()));
            alertContent = alarmProcessLogger.getAlertMessage();
        } else if (alertType.equalsIgnoreCase(AlertType.RECOVER)) {
            AlertLog alertLog = this.alertLogRepository.selectLatest(alarmProcessLogger.getAlarmContract().getId(), AlertType.PROBLEM, SilenceStatus.NO).get();
            alertContent = "消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n" + alertLog.getContent();
            alarmMessageBO.setContent(alertContent);
        }
        alarmMessageBO.setTitle(String.format("[%s][id:%s]%s", Strings.isNullOrEmpty(messageTitle) ? alertTitle() : messageTitle, alarmProcessLogger.getAlarmContract().getId(), alarmProcessLogger.getAlarmContract().getAlarmName()));
        alarmMessageBO.setRecipients(recipients);
        alarmMessageBO.setWays(generateWays(alertContract));
        alarmMessageBO.setDingHook(alertContract.getDingRobotHook());
        alarmMessageBO.setHttpPostEndpoint(alertContract.getHttpPostUrl());
        alarmMessageBO.setWechatHook(alertContract.getWechatRobotHook());
        alarmMessageBO.setFeiShuHook(alertContract.getFeishuRobotHook());
        alarmMessageBO.setContext(alarmProcessLogger.getContext());

        messageService.send(alarmMessageBO);

        saveAlertLog(alertType, alarmMessageBO.getResultList(), recipients, alarmProcessLogger.getAlarmContract().getId(),
                alertContent, alarmProcessLogger.getAlarmLog().getId());
    }

    private List<MessageWay> generateWays(AlertContract alertContract) {
        List<MessageWay> messageWays = new ArrayList<>();
        for (String way : alertContract.getWays()) {
            if ("email".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.EMAIL);
            }
            if ("sms".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.SMS);
            }
            if ("wechat".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.WECHAT);
            }
            if ("wechat".equalsIgnoreCase(way) && StringUtils.isNotBlank(alertContract.getWechatRobotHook())) {
                messageWays.add(MessageWay.WECHAT_ROBOT);
            }
            if ("dingding".equalsIgnoreCase(way)) {
                messageWays.add(MessageWay.DING_DING);
            }
            if ("dingding".equalsIgnoreCase(way) && StringUtils.isNotBlank(alertContract.getDingRobotHook())) {
                messageWays.add(MessageWay.DING_ROBOT);
            }
            if ("feishu".equalsIgnoreCase(way) && StringUtils.isNotBlank(alertContract.getFeishuRobotHook())) {
                messageWays.add(MessageWay.FEI_SHU_ROBOT);
            }
            if ("http_post".equalsIgnoreCase(way) && StringUtils.isNotBlank(alertContract.getHttpPostUrl())) {
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

    private void saveAlertLog(String alertType, List<MessageResult> messageResults,
                              List<AccountInfo> accountInfos, Long alarmId, String content, Long executeId) {
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
        //if not alert, check if send recover message
        if (needRecover(latestAlarmLog, alarmProcessLogger)) {
            sendAlert(alarmProcessLogger, recipients, AlertType.RECOVER);
        } else {
            alarmLog(alarmProcessLogger);
        }
    }

    private boolean needRecover(Optional<AlarmLog> latestAlarmLog, AlarmProcessLogger alarmProcessLogger) {
        //if not alert, check if send recover message
        if (latestAlarmLog.isPresent() && latestAlarmLog.get().getVerifyResult().equalsIgnoreCase(VerifyResult.TRUE)) {
            //this is recover message
            // 开启恢复通知
            return RecoverNoticeStatus.OPEN.name().equalsIgnoreCase(alarmProcessLogger.getAlarmContract().getRecoverNoticeStatus());
        }
        return false;
    }

    private boolean checkSilence(AlarmProcessLogger alarmProcessLogger) {
        Long alarmId = alarmProcessLogger.getAlarmContract().getId();
        Optional<AlarmLog> latestNoProblemAlarmLog = alarmLogRepository.selectLatest(alarmId, VerifyResult.FALSE);
        Long current = System.currentTimeMillis();
        Long silenceThreshold = alarmProcessLogger.getAlarmContract().getAlertContract().getSilence() * 60 * 1000;
        if (latestNoProblemAlarmLog.isPresent() && current - latestNoProblemAlarmLog.get().getCreateAt().getTime() < silenceThreshold) {
            return true;
        }
        //find latest problem and not silence alert time
        Optional<AlertLog> latestAlertLog = alertLogRepository.selectLatest(alarmId, AlertType.PROBLEM, SilenceStatus.NO);
        if (latestAlertLog.isPresent() && current - latestAlertLog.get().getCreateAt().getTime() < silenceThreshold) {
            return true;
        }

        return false;
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
    }

    private void processProblem(AlarmProcessLogger
                                        alarmProcessLogger, List<AccountInfo> recipients, Optional<AlarmLog> latestAlarmLog) {
        if (!latestAlarmLog.isPresent() || latestAlarmLog.get().getVerifyResult().equalsIgnoreCase(VerifyResult.FALSE)) {
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
        alarmLog.setCost((int) (alarmProcessLogger.getEnd().getMillis() - alarmProcessLogger.getStart().getMillis()));
        alarmLog.setCreateAt(new Date());
        alarmLog.setExeStart(alarmProcessLogger.getStart().toDate());
        alarmLog.setExeEnd(alarmProcessLogger.getEnd().toDate());
        alarmLog.setExecuteResult(alarmProcessLogger.getExecuteStatus().getName());
        alarmLog.setMessage(alarmProcessLogger.traceInfo());
        Boolean alert = alarmProcessLogger.getAlert();
        alarmLog.setVerifyResult(alert != null && alert ? VerifyResult.TRUE : VerifyResult.FALSE);
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
