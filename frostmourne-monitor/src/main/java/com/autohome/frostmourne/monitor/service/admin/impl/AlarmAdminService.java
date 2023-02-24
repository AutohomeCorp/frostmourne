package com.autohome.frostmourne.monitor.service.admin.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.RecipientType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.*;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.*;
import com.autohome.frostmourne.monitor.model.contract.*;
import com.autohome.frostmourne.monitor.model.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.model.enums.AlarmUpgradeStatus;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.core.service.IServiceInfoService;
import com.autohome.frostmourne.monitor.transform.DataNameTransformer;
import com.autohome.frostmourne.monitor.transform.DataSourceTransformer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;
import org.springframework.util.CollectionUtils;

@Service
public class AlarmAdminService implements IAlarmAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmAdminService.class);

    private static final HashMap<String, String> metricRuleMap = new HashMap<String, String>() {
        {
            put("numeric", "numeric");
            put("ring_compare", "percentage");
            put("same_time", "percentage");
            put("object", "expression");
            put("bucket_numeric", "bucket_numeric");
            put("ping", "ping");
            put("telnet", "ping");
        }
    };

    @Resource
    private IAlarmRepository alarmRepository;

    @Resource
    private IRecipientRepository recipientRepository;

    @Resource(name = "frostmourneTransactionManager")
    private DataSourceTransactionManager frostmourneTransactionManager;

    @Resource
    private IAlertRepository alertRepository;

    @Resource
    private IAlertUpgradeRepository alertUpgradeRepository;

    @Resource
    private IRulePropertyRepository rulePropertyRepository;

    @Resource
    private IRuleRepository ruleRepository;

    @Resource
    private IMetricRepository metricRepository;

    @Resource
    private IDataSourceRepository dataSourceRepository;

    @Resource
    private IDataNameRepository dataNameRepository;

    @Resource
    private IServiceInfoService serviceInfoService;

    @Override
    public boolean atomicSave(AlarmContract alarmContract) {
        boolean isValidCron = CronExpression.isValidExpression(alarmContract.getCron());
        if (!isValidCron) {
            throw new ProtocolException(510, "cron表达式非法");
        }
        padAlarm(alarmContract);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = frostmourneTransactionManager.getTransaction(def);
        try {
            save(alarmContract);
        } catch (Exception ex) {
            frostmourneTransactionManager.rollback(status);
            LOGGER.error("error when save alarm", ex);
            throw ex;
        }
        frostmourneTransactionManager.commit(status);
        return true;
    }

    @Override
    public boolean delete(Long alarmId) {
        Optional<Alarm> optionalAlarm = alarmRepository.selectByPrimaryKey(alarmId);
        if (!optionalAlarm.isPresent()) {
            return false;
        }
        Alarm alarm = optionalAlarm.get();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = frostmourneTransactionManager.getTransaction(def);
        try {
            alarmRepository.deleteByPrimaryKey(alarmId);
            alertRepository.deleteByAlarm(alarmId);
            metricRepository.deleteByAlarm(alarmId);
            ruleRepository.deleteByAlarm(alarmId);
            rulePropertyRepository.deleteByAlarm(alarmId);
            recipientRepository.deleteByAlarm(alarmId);
        } catch (Exception ex) {
            frostmourneTransactionManager.rollback(status);
        }
        frostmourneTransactionManager.commit(status);
        return true;
    }

    @Transactional(value = "frostmourneTransactionManager")
    @Override
    public boolean open(Long alarmId) {
        return updateStatus(alarmId, AlarmStatus.OPEN);
    }

    @Transactional(value = "frostmourneTransactionManager")
    @Override
    public boolean close(Long alarmId) {
        return updateStatus(alarmId, AlarmStatus.CLOSE);
    }

    @Override
    public AlarmContract findById(Long alarmId) {
        AlarmContract alarmContract = new AlarmContract();
        Optional<Alarm> optionalAlarm = alarmRepository.selectByPrimaryKey(alarmId);
        Alarm alarm = optionalAlarm.orElseThrow(() -> new ProtocolException(404, String.format("The alarm[id=%s] not exists", alarmId)));
        alarmContract.setId(alarmId);
        alarmContract.setStatus(alarm.getStatus());
        alarmContract.setOwnerKey(alarm.getOwnerKey());
        alarmContract.setDescription(alarm.getDescription());
        alarmContract.setCron(alarm.getCron());
        alarmContract.setTeamName(alarm.getTeamName());
        alarmContract.setAlarmType(alarm.getAlarmType());
        alarmContract.setAlarmName(alarm.getAlarmName());
        alarmContract.setExecuteResult(alarm.getExecuteResult());
        alarmContract.setExecuteAt(alarm.getExecuteAt());
        alarmContract.setJobId(alarm.getJobId());
        alarmContract.setRiskLevel(alarm.getRiskLevel());
        alarmContract.setRecoverNoticeStatus(alarm.getRecoverNoticeStatus());

        MetricContract metricContract = new MetricContract();
        Optional<Metric> optionalMetric = this.metricRepository.findOneByAlarm(alarmId);
        if (!optionalMetric.isPresent()) {
            throw new ProtocolException(20200229, "find no metric, alarmId: " + alarmId);
        }
        Metric metric = optionalMetric.get();
        metricContract.setAggregationType(metric.getAggregationType());
        metricContract.setAggregationField(metric.getAggregationField());
        metricContract.setQueryString(metric.getQueryString());
        metricContract.setDataSourceId(metric.getDataSourceId());
        metricContract.setDataName(metric.getDataName());
        metricContract.setMetricType(metric.getMetricType());
        metricContract.setDataNameId(metric.getDataNameId());
        metricContract.setAlarmId(alarmId);
        metricContract.setPostData(metric.getPostData());
        metricContract.setBucketType(metric.getBucketType());
        metricContract.setBucketField(metric.getBucketField());
        metricContract.setProperties(JacksonUtil.deSerialize(metric.getProperties(), new TypeReference<Map<String, Object>>() {}));
        if (metric.getDataSourceId() != null && metric.getDataSourceId() > 0) {
            Optional<DataSource> optionalDataSource = dataSourceRepository.selectByPrimaryKey(metric.getDataSourceId());
            DataSourceContract dataSourceContract = optionalDataSource.map(DataSourceTransformer::model2Contract)
                .orElseThrow(() -> new ProtocolException(1890, "datasource not exist. id: " + metric.getDataSourceId()));
            metricContract.setDataSourceContract(dataSourceContract);
        }

        if (metric.getDataNameId() != null && metric.getDataNameId() > 0) {
            Optional<DataName> optionalDataName = dataNameRepository.selectByPrimaryKey(metric.getDataNameId());
            if (!optionalDataName.isPresent()) {
                LOGGER.error("dataName not exist. " + metric.getDataNameId());
                throw new ProtocolException(1209, "dataName not exist. " + metric.getDataNameId());
            }
            DataNameContract dataNameContract = DataNameTransformer.model2Contract(optionalDataName.get());
            metricContract.setDataNameContract(dataNameContract);
        }

        alarmContract.setMetricContract(metricContract);

        RuleContract ruleContract = new RuleContract();
        Optional<Rule> optionalRule = this.ruleRepository.findOneByAlarm(alarmId);
        if (!optionalRule.isPresent()) {
            throw new ProtocolException(7292346, "alarm has no rule, alarmId: " + alarmId);
        }
        Rule rule = optionalRule.get();
        ruleContract.setAlertTemplateType(rule.getAlertTemplateType());
        ruleContract.setAlertTemplate(rule.getAlertTemplate());
        ruleContract.setRuleType(rule.getRuleType());
        ruleContract.setAlarmId(alarmId);
        Map<String, String> rulePropertyMap = new HashMap<>();

        List<RuleProperty> rulePropertyList = this.rulePropertyRepository.findByRuleId(rule.getId());
        if (rulePropertyList != null && rulePropertyList.size() > 0) {
            for (RuleProperty ruleProperty : rulePropertyList) {
                rulePropertyMap.put(ruleProperty.getPropKey(), ruleProperty.getPropValue());
            }
        }
        ruleContract.setSettings(rulePropertyMap);
        alarmContract.setRuleContract(ruleContract);

        AlertContract alertContract = new AlertContract();
        Alert alert = this.alertRepository.findOneByAlarm(alarmId).get();
        alertContract.setSilence(alert.getSilence());
        alertContract.setSilenceExpression(alert.getSilenceExpression());
        alertContract.setWays(Splitter.on(",").splitToList(alert.getWays()));
        alertContract.setAlarm_id(alarmId);
        alertContract.setAllowSmsFrom(alert.getAllowSmsFrom());
        alertContract.setAllowSmsTo(alert.getAllowSmsTo());
        alertContract.setDingRobotHook(alert.getDingRobotHook());
        alertContract.setHttpPostUrl(alert.getHttpPostUrl());
        alertContract.setWechatRobotHook(alert.getWechatRobotHook());
        alertContract.setCreateAt(alert.getCreateAt());
        alertContract.setFeishuRobotHook(alert.getFeishuRobotHook());
        alertContract.setOneMessageRobotHook(alert.getOneMessageRobotHook());

        List<Recipient> alertRecipientList = this.recipientRepository.findByAlarmAndType(alarmId, RecipientType.ALERT);
        alertContract.setRecipients(alertRecipientList.stream().map(Recipient::getAccount).collect(Collectors.toList()));
        alarmContract.setAlertContract(alertContract);

        AlertUpgradeContract alertUpgradeContract = new AlertUpgradeContract();
        // default values for old version
        alertUpgradeContract.setStatus(AlarmUpgradeStatus.CLOSE);
        alertUpgradeContract.setWays(new ArrayList<>());
        AlertUpgrade alertUpgrade = alertUpgradeRepository.findOneByAlarmId(alarmId);
        if (alertUpgrade != null) {
            alertUpgradeContract.setStatus(alertUpgrade.getStatus());
            alertUpgradeContract.setTimesToUpgrade(alertUpgrade.getTimesToUpgrade());
            if (StringUtils.isNotBlank(alertUpgrade.getWays())) {
                alertUpgradeContract.setWays(Splitter.on(",").splitToList(alertUpgrade.getWays()));
            }
            List<Recipient> alertUpgradeRecipientList = this.recipientRepository.findByAlarmAndType(alarmId, RecipientType.ALERT_UPGRADE);
            alertUpgradeContract.setRecipients(alertUpgradeRecipientList.stream().map(Recipient::getAccount).collect(Collectors.toList()));
            alertUpgradeContract.setDingRobotHook(alertUpgrade.getDingRobotHook());
            alertUpgradeContract.setHttpPostUrl(alertUpgrade.getHttpPostUrl());
            alertUpgradeContract.setWechatRobotHook(alertUpgrade.getWechatRobotHook());
            alertUpgradeContract.setFeishuRobotHook(alertUpgrade.getFeishuRobotHook());
            alertUpgradeContract.setOneMessageRobotHook(alertUpgrade.getOneMessageRobotHook());
        }
        alarmContract.setAlertUpgradeContract(alertUpgradeContract);

        if (Optional.ofNullable(alarm.getServiceId()).orElse(0L) > 0L) {
            alarmContract.setServiceInfo(serviceInfoService.getSimpleContract(alarm.getServiceId()).orElse(null));
        }
        return alarmContract;
    }

    @Override
    public PagerContract<Alarm> find(int pageIndex, int pageSize, Long alarmId, String name, String teamName, AlarmStatus status, Long serviceId) {
        return alarmRepository.findPage(pageIndex, pageSize, alarmId, name, teamName, status, serviceId);
    }

    @Override
    public void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status) {
        alarmRepository.updateAlarmLastExecuteInfo(alarmId, executeTime, status);
    }

    @Override
    public void updateTeamNameByIdList(List<Long> alarmIdList, String newTeamName) {
        this.alarmRepository.updateTeamNameByIdList(alarmIdList, newTeamName);
    }

    private boolean updateStatus(Long alarmId, AlarmStatus status) {
        return alarmRepository.updateStatus(alarmId, status) > 0;
    }

    public void save(AlarmContract alarmContract) {

        // 1. save alarm
        boolean isNewAlarm = (alarmContract.getId() == null) || (alarmContract.getId() == 0);
        Alarm alarm;
        if (isNewAlarm) {
            alarm = addAlarm(alarmContract);
        } else {
            alarm = updateAlarm(alarmContract);
        }
        Long alarmId = alarm.getId();
        saveAlert(alarmContract.getAlertContract(), alarmId, isNewAlarm, alarmContract.getOperator());
        saveAlertUpgrade(alarmContract.getAlertUpgradeContract(), alarmId, isNewAlarm, alarmContract.getOperator());
        Long ruleId = saveRule(alarmContract.getRuleContract(), alarmId, isNewAlarm, alarmContract.getOperator());
        saveMetric(alarmContract.getMetricContract(), alarmId, ruleId, isNewAlarm, alarmContract.getOperator());
    }

    private void saveAlertUpgrade(AlertUpgradeContract alertUpgradeContract, Long alarmId, boolean isNewAlarm, String operator) {
        if (!isNewAlarm) {
            alertUpgradeRepository.deleteByAlarmId(alarmId);
            recipientRepository.deleteByAlarmAndType(alarmId, RecipientType.ALERT_UPGRADE);
        }
        AlertUpgrade alertUpgrade = new AlertUpgrade();
        alertUpgrade.setAlarmId(alarmId);
        alertUpgrade.setStatus(alertUpgradeContract.getStatus());
        alertUpgrade.setTimesToUpgrade(alertUpgradeContract.getTimesToUpgrade());
        alertUpgrade.setWays(String.join(",", alertUpgradeContract.getWays()));
        alertUpgrade.setDingRobotHook(alertUpgradeContract.getDingRobotHook());
        alertUpgrade.setHttpPostUrl(alertUpgradeContract.getHttpPostUrl());
        alertUpgrade.setWechatRobotHook(alertUpgradeContract.getWechatRobotHook());
        alertUpgrade.setFeishuRobotHook(alertUpgradeContract.getFeishuRobotHook());
        alertUpgrade.setOneMessageRobotHook(alertUpgradeContract.getOneMessageRobotHook());
        alertUpgrade.setCreator(operator);
        alertUpgrade.setCreateAt(LocalDateTime.now());
        alertUpgradeRepository.insert(alertUpgrade);

        if (!CollectionUtils.isEmpty(alertUpgradeContract.getRecipients())){
            for (String recipient : alertUpgradeContract.getRecipients()) {
                Recipient alertRecipient = new Recipient();
                alertRecipient.setAlarmId(alarmId);
                alertRecipient.setAlertId(alertUpgrade.getId());
                alertRecipient.setType(RecipientType.ALERT_UPGRADE);
                alertRecipient.setAccount(recipient);
                alertRecipient.setCreateAt(new Date());
                recipientRepository.insert(alertRecipient);
            }
        }
    }

    private Alarm addAlarm(AlarmContract alarmContract) {
        Alarm alarm = new Alarm();

        alarm.setAlarmName(alarmContract.getAlarmName());
        alarm.setAlarmType(alarmContract.getAlarmType());
        alarm.setCreator(alarmContract.getOperator());
        alarm.setCron(alarmContract.getCron());
        alarm.setTeamName(alarmContract.getTeamName());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setModifier(alarmContract.getOperator());
        alarm.setOwnerKey(alarmContract.getOwnerKey());
        alarm.setStatus(alarmContract.getStatus());
        alarm.setRiskLevel(alarmContract.getRiskLevel());
        alarm.setRecoverNoticeStatus(alarmContract.getRecoverNoticeStatus());
        Date now = new Date();
        alarm.setCreateAt(now);
        alarm.setModifyAt(now);
        alarm.setJobId(0L);
        alarm.setExecuteResult(ExecuteStatus.WAITING);
        alarm.setServiceId(Optional.ofNullable(alarmContract.getServiceInfo()).map(ServiceInfoSimpleContract::getId).orElse(null));
        alarm.setTriggerLastTime(0L);
        alarm.setTriggerNextTime(0L);
        alarmRepository.insert(alarm);

        return alarm;
    }

    private Alarm updateAlarm(AlarmContract alarmContract) {
        Date now = new Date();

        Alarm alarm = new Alarm();
        alarm.setId(alarmContract.getId());
        alarm.setAlarmName(alarmContract.getAlarmName());
        alarm.setAlarmType(alarmContract.getAlarmType());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setOwnerKey(alarmContract.getOwnerKey());
        alarm.setStatus(alarmContract.getStatus());
        alarm.setRiskLevel(alarmContract.getRiskLevel());
        alarm.setRecoverNoticeStatus(alarmContract.getRecoverNoticeStatus());
        alarm.setCron(alarmContract.getCron());
        alarm.setModifyAt(now);
        alarm.setModifier(alarmContract.getOperator());
        alarm.setTeamName(alarmContract.getTeamName());
        alarm.setServiceId(Optional.ofNullable(alarmContract.getServiceInfo()).map(ServiceInfoSimpleContract::getId).orElse(null));
        alarm.setTriggerNextTime(0L);
        alarmRepository.updateByPrimaryKeySelective(alarm);

        alarm = alarmRepository.selectByPrimaryKey(alarmContract.getId()).get();
        return alarm;
    }

    private void saveAlert(AlertContract contract, Long alarmId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            recipientRepository.deleteByAlarmAndType(alarmId, RecipientType.ALERT);
            alertRepository.deleteByAlarm(alarmId);
        }
        Alert alert = new Alert();
        alert.setWays(String.join(",", contract.getWays()));
        alert.setAlarmId(alarmId);
        alert.setSilence(contract.getSilence());
        alert.setSilenceExpression(contract.getSilenceExpression());
        alert.setAllowSmsFrom(contract.getAllowSmsFrom());
        alert.setAllowSmsTo(contract.getAllowSmsTo());
        alert.setCreator(account);
        alert.setCreateAt(new Date());
        alert.setDingRobotHook(contract.getDingRobotHook());
        alert.setHttpPostUrl(contract.getHttpPostUrl());
        alert.setWechatRobotHook(contract.getWechatRobotHook());
        alert.setFeishuRobotHook(contract.getFeishuRobotHook());
        alert.setOneMessageRobotHook(contract.getOneMessageRobotHook());
        alertRepository.insert(alert);

        for (String recipient : contract.getRecipients()) {
            Recipient alertRecipient = new Recipient();
            alertRecipient.setAlarmId(alarmId);
            alertRecipient.setAlertId(alert.getId());
            alertRecipient.setAccount(recipient);
            alertRecipient.setType(RecipientType.ALERT);
            alertRecipient.setCreateAt(new Date());
            recipientRepository.insert(alertRecipient);
        }
    }

    private Long saveRule(RuleContract ruleContract, Long alarmId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            rulePropertyRepository.deleteByAlarm(alarmId);
            ruleRepository.deleteByAlarm(alarmId);
        }

        Rule rule = new Rule();
        rule.setAlarmId(alarmId);
        rule.setAlertTemplateType(ruleContract.getAlertTemplateType());
        rule.setAlertTemplate(ruleContract.getAlertTemplate());
        rule.setCreator(account);
        rule.setCreateAt(new Date());
        rule.setRuleType(ruleContract.getRuleType());
        ruleRepository.insert(rule);
        Long ruleId = rule.getId();
        if (ruleContract.getSettings() != null) {
            for (Map.Entry<String, String> entry : ruleContract.getSettings().entrySet()) {
                RuleProperty ruleProperty = new RuleProperty();
                ruleProperty.setAlarmId(alarmId);
                ruleProperty.setCreateAt(new Date());
                ruleProperty.setPropKey(entry.getKey());
                ruleProperty.setPropValue(entry.getValue());
                ruleProperty.setRuleId(ruleId);
                ruleProperty.setCreator(account);
                rulePropertyRepository.insert(ruleProperty);
            }
        }

        return ruleId;
    }

    private void saveMetric(MetricContract metricContract, Long alarmId, Long ruleId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            metricRepository.deleteByAlarm(alarmId);
        }

        Metric metric = new Metric();
        metric.setAlarmId(alarmId);
        metric.setCreator(account);
        metric.setRuleId(ruleId);
        metric.setAggregationType(metricContract.getAggregationType());
        metric.setAggregationField(metricContract.getAggregationField());
        metric.setDataName(metricContract.getDataName());
        metric.setDataSourceId(metricContract.getDataSourceId());
        metric.setDataNameId(metricContract.getDataNameId());
        metric.setMetricType(metricContract.getMetricType());
        metric.setQueryString(metricContract.getQueryString());
        metric.setPostData(metricContract.getPostData());
        metric.setProperties(JacksonUtil.serialize(metricContract.getProperties()));
        metric.setCreateAt(new Date());
        metric.setBucketType(metricContract.getBucketType());
        metric.setBucketField(metricContract.getBucketField());
        metricRepository.insert(metric);
    }

    @Override
    public void padAlarm(AlarmContract alarmContract) {
        String dataNameStr = alarmContract.getMetricContract().getDataName();
        alarmContract.setAlarmType(dataNameStr);
        String ruleType = metricRuleMap.get(alarmContract.getMetricContract().getMetricType());
        alarmContract.getRuleContract().setRuleType(ruleType);

        if (dataNameStr.equalsIgnoreCase("http")
                || dataNameStr.equalsIgnoreCase("ping")
                || dataNameStr.equalsIgnoreCase("telnet")) {
            return;
        }

        Optional<DataName> optionalDataName = dataNameRepository.findByName(dataNameStr);
        DataName dataName = optionalDataName.orElseThrow(() -> new ProtocolException(1290, "dataName not exist. " + dataNameStr));
        alarmContract.getMetricContract().setDataNameId(dataName.getId());
        alarmContract.getMetricContract().setDataNameContract(DataAdminService.toDataNameContract(dataName));
        alarmContract.getMetricContract().setDataSourceId(dataName.getDataSourceId());

        Optional<DataSource> optionalDataSource = dataSourceRepository.selectByPrimaryKey(dataName.getDataSourceId());
        DataSourceContract dataSourceContract = optionalDataSource.map(DataSourceTransformer::model2Contract)
            .orElseThrow(() -> new ProtocolException(1900, "dataSource not exist. id: " + dataName.getDataSourceId()));
        alarmContract.getMetricContract().setDataSourceContract(dataSourceContract);
    }
}
