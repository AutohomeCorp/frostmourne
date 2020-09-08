package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.AlertContract;
import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.RuleContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSimpleContract;
import com.autohome.frostmourne.monitor.contract.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.contract.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alert;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Metric;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Recipient;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Rule;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.RuleProperty;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataNameRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataSourceRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IMetricRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IRecipientRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IRulePropertyRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IRuleRepository;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.admin.IScheduleService;
import com.autohome.frostmourne.monitor.service.core.service.IServiceInfoService;
import com.autohome.frostmourne.monitor.transform.DataNameTransformer;
import com.autohome.frostmourne.monitor.transform.DataSourceTransformer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;
import org.apache.logging.log4j.core.util.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class AlarmAdminService implements IAlarmAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmAdminService.class);

    private static final HashMap<String, String> metricRuleMap = new HashMap<String, String>() {
        {
            put("numeric", "numeric");
            put("ring_than", "percentage");
            put("same_time", "percentage");
            put("object", "expression");
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
    private IScheduleService scheduleService;

    @Resource
    private IServiceInfoService serviceInfoService;

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
            scheduleService.removeJob(Math.toIntExact(alarm.getJobId()));
        } catch (Exception ex) {
            frostmourneTransactionManager.rollback(status);
        }
        frostmourneTransactionManager.commit(status);
        return true;
    }

    @Transactional(value = "frostmourneTransactionManager")
    public boolean open(Long alarmId) {
        boolean result = updateStatus(alarmId, AlarmStatus.OPEN);
        Alarm alarm = this.alarmRepository.selectByPrimaryKey(alarmId).get();
        this.scheduleService.openJob(Math.toIntExact(alarm.getJobId()));
        return result;
    }

    @Transactional(value = "frostmourneTransactionManager")
    public boolean close(Long alarmId) {
        boolean result = updateStatus(alarmId, AlarmStatus.CLOSE);
        Alarm alarm = this.alarmRepository.selectByPrimaryKey(alarmId).get();
        this.scheduleService.closeJob(Math.toIntExact(alarm.getJobId()));
        return result;
    }

    public AlarmContract findById(Long alarmId) {
        AlarmContract alarmContract = new AlarmContract();
        Optional<Alarm> optionalAlarm = alarmRepository.selectByPrimaryKey(alarmId);
        if (!optionalAlarm.isPresent()) {
            return null;
        }
        Alarm alarm = optionalAlarm.get();
        alarmContract.setId(alarmId);
        alarmContract.setStatus(alarm.getStatus());
        alarmContract.setOwner_key(alarm.getOwnerKey());
        alarmContract.setDescription(alarm.getDescription());
        alarmContract.setCron(alarm.getCron());
        alarmContract.setTeam_name(alarm.getTeamName());
        alarmContract.setAlarm_type(alarm.getAlarmType());
        alarmContract.setAlarm_name(alarm.getAlarmName());
        alarmContract.setExecute_result(alarm.getExecuteResult());
        alarmContract.setExecute_at(alarm.getExecuteAt());
        alarmContract.setJob_id(alarm.getJobId());
        alarmContract.setRisk_level(alarm.getRiskLevel());

        MetricContract metricContract = new MetricContract();
        Optional<Metric> optionalMetric = this.metricRepository.findOneByAlarm(alarmId);
        if (!optionalMetric.isPresent()) {
            throw new ProtocolException(20200229, "find no metric, alarmId: " + alarmId);
        }
        Metric metric = optionalMetric.get();
        metricContract.setAggregation_type(metric.getAggregationType());
        metricContract.setAggregation_field(metric.getAggregationField());
        metricContract.setQuery_string(metric.getQueryString());
        metricContract.setDataSourceId(metric.getDataSourceId());
        metricContract.setDataName(metric.getDataName());
        metricContract.setMetric_type(metric.getMetricType());
        metricContract.setDataName_id(metric.getDataNameId());
        metricContract.setAlarm_id(alarmId);
        metricContract.setPost_data(metric.getPostData());
        metricContract.setProperties(JacksonUtil.deSerialize(metric.getProperties(), new TypeReference<Map<String, Object>>() {
        }));
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
        ruleContract.setAlert_template(rule.getAlertTemplate());
        ruleContract.setRule_type(rule.getRuleType());
        ruleContract.setAlarm_id(alarmId);
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
        alertContract.setWays(Splitter.on(",").splitToList(alert.getWays()));
        alertContract.setAlarm_id(alarmId);
        alertContract.setAllow_sms_from(alert.getAllowSmsFrom());
        alertContract.setAllow_sms_to(alert.getAllowSmsTo());
        alertContract.setDing_robot_hook(alert.getDingRobotHook());
        alertContract.setHttp_post_url(alert.getHttpPostUrl());
        alertContract.setWechat_robot_hook(alert.getWechatRobotHook());
        alertContract.setCreateAt(alert.getCreateAt());

        List<Recipient> recipientList = this.recipientRepository.findByAlarm(alarmId);
        alertContract.setRecipients(recipientList.stream().map(Recipient::getAccount).collect(Collectors.toList()));
        alarmContract.setAlertContract(alertContract);

        if (Optional.ofNullable(alarm.getServiceId()).orElse(0L) > 0L) {
            alarmContract.setServiceInfo(serviceInfoService.getSimpleContract(alarm.getServiceId()).orElse(null));
        }
        return alarmContract;
    }

    public PagerContract<Alarm> find(int pageIndex, int pageSize, Long alarmId, String name,
                                     String teamName, String status, Long serviceId) {
        return alarmRepository.findPage(pageIndex, pageSize, alarmId, name, teamName, status, serviceId);
    }


    @Override
    public void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status) {
        alarmRepository.updateAlarmLastExecuteInfo(alarmId, executeTime, status.getName());
    }

    private boolean updateStatus(Long alarmId, String status) {
        return alarmRepository.updateStatus(alarmId, status) > 0;
    }

    public void save(AlarmContract alarmContract) {

        //1. save alarm
        boolean isNewAlarm = (alarmContract.getId() == null) || (alarmContract.getId() == 0);
        Alarm alarm = null;
        if (isNewAlarm) {
            alarm = addAlarm(alarmContract);
        } else {
            alarm = updateAlarm(alarmContract);
        }
        Long alarmId = alarm.getId();
        saveAlert(alarmContract.getAlertContract(), alarmId, isNewAlarm, alarmContract.getOperator());
        Long ruleId = saveRule(alarmContract.getRuleContract(), alarmId, isNewAlarm, alarmContract.getOperator());
        saveMetric(alarmContract.getMetricContract(), alarmId, ruleId, isNewAlarm, alarmContract.getOperator());

        saveJobSchedule(isNewAlarm, alarm);
    }

    private Alarm addAlarm(AlarmContract alarmContract) {
        Alarm alarm = new Alarm();

        alarm.setAlarmName(alarmContract.getAlarm_name());
        alarm.setAlarmType(alarmContract.getAlarm_type());
        alarm.setCreator(alarmContract.getOperator());
        alarm.setCron(alarmContract.getCron());
        alarm.setTeamName(alarmContract.getTeamName());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setModifier(alarmContract.getOperator());
        alarm.setOwnerKey(alarmContract.getOwner_key());
        alarm.setStatus(alarmContract.getStatus());
        alarm.setRiskLevel(alarmContract.getRisk_level());
        Date now = new Date();
        alarm.setCreateAt(now);
        alarm.setModifyAt(now);
        alarm.setJobId(0L);
        alarm.setExecuteResult(ExecuteStatus.WAITING.getName());
        alarm.setServiceId(Optional.ofNullable(alarmContract.getServiceInfo()).map(ServiceInfoSimpleContract::getId).orElse(null));
        alarmRepository.insert(alarm);

        return alarm;
    }

    private Alarm updateAlarm(AlarmContract alarmContract) {
        Date now = new Date();

        Alarm alarm = new Alarm();
        alarm.setId(alarmContract.getId());
        alarm.setAlarmName(alarmContract.getAlarm_name());
        alarm.setAlarmType(alarmContract.getAlarm_type());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setOwnerKey(alarmContract.getOwner_key());
        alarm.setStatus(alarmContract.getStatus());
        alarm.setRiskLevel(alarmContract.getRisk_level());
        alarm.setCron(alarmContract.getCron());
        alarm.setModifyAt(now);
        alarm.setModifier(alarmContract.getOperator());
        alarm.setTeamName(alarmContract.getTeamName());
        alarm.setServiceId(Optional.ofNullable(alarmContract.getServiceInfo()).map(ServiceInfoSimpleContract::getId).orElse(null));

        alarmRepository.updateByPrimaryKeySelective(alarm);

        alarm = alarmRepository.selectByPrimaryKey(alarmContract.getId()).get();
        return alarm;
    }

    private void saveAlert(AlertContract contract, Long alarmId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            recipientRepository.deleteByAlarm(alarmId);
            alertRepository.deleteByAlarm(alarmId);
        }
        Alert alert = new Alert();
        alert.setWays(String.join(",", contract.getWays()));
        alert.setAlarmId(alarmId);
        alert.setSilence(contract.getSilence());
        alert.setAllowSmsFrom(contract.getAllow_sms_from());
        alert.setAllowSmsTo(contract.getAllow_sms_to());
        alert.setCreator(account);
        alert.setCreateAt(new Date());
        alert.setDingRobotHook(contract.getDing_robot_hook());
        alert.setHttpPostUrl(contract.getHttp_post_url());
        alert.setWechatRobotHook(contract.getWechat_robot_hook());
        alertRepository.insert(alert);

        for (String recipient : contract.getRecipients()) {
            Recipient alertRecipient = new Recipient();
            alertRecipient.setAlarmId(alarmId);
            alertRecipient.setAlertId(alert.getId());
            alertRecipient.setAccount(recipient);
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
        rule.setAlertTemplate(ruleContract.getAlert_template());
        rule.setCreator(account);
        rule.setCreateAt(new Date());
        rule.setRuleType(ruleContract.getRule_type());
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
        metric.setAggregationType(metricContract.getAggregation_type());
        metric.setAggregationField(metricContract.getAggregation_field());
        metric.setDataName(metricContract.getDataName());
        metric.setDataSourceId(metricContract.getDataSourceId());
        metric.setDataNameId(metricContract.getDataName_id());
        metric.setMetricType(metricContract.getMetric_type());
        metric.setQueryString(metricContract.getQuery_string());
        metric.setPostData(metricContract.getPost_data());
        metric.setProperties(JacksonUtil.serialize(metricContract.getProperties()));
        metric.setCreateAt(new Date());
        metricRepository.insert(metric);
    }

    public void padAlarm(AlarmContract alarmContract) {
        alarmContract.setAlarm_type(alarmContract.getMetricContract().getDataName());
        String ruleType = metricRuleMap.get(alarmContract.getMetricContract().getMetric_type());
        alarmContract.getRuleContract().setRule_type(ruleType);

        if (!alarmContract.getMetricContract().getDataName().equalsIgnoreCase("http")) {
            Optional<DataName> optionalDataName = dataNameRepository.findByName(alarmContract.getMetricContract().getDataName());
            if (!optionalDataName.isPresent()) {
                throw new ProtocolException(1290, "dataName not exist. " + alarmContract.getMetricContract().getDataName());
            }
            DataName dataName = optionalDataName.get();
            alarmContract.getMetricContract().setDataName_id(dataName.getId());
            alarmContract.getMetricContract().setDataNameContract(DataAdminService.toDataNameContract(dataName));
            alarmContract.getMetricContract().setDataSourceId(dataName.getDataSourceId());

            Optional<DataSource> optionalDataSource = dataSourceRepository.selectByPrimaryKey(dataName.getDataSourceId());
            DataSourceContract dataSourceContract = optionalDataSource.map(DataSourceTransformer::model2Contract)
                    .orElseThrow(() -> new ProtocolException(1900, "dataSource not exist. id: " + dataName.getDataSourceId()));
            alarmContract.getMetricContract().setDataSourceContract(dataSourceContract);
        }
    }

    private void saveJobSchedule(boolean isNewAlarm, Alarm alarm) {
        if (isNewAlarm || alarm.getJobId() <= 0) {
            Integer jobId = this.scheduleService.addJob(alarm.getId(), alarm.getCron(), alarm.getStatus());
            alarmRepository.updateJobId(alarm.getId(), new Long(jobId));
        } else {
            this.scheduleService.updateJob(alarm.getId(), Math.toIntExact(alarm.getJobId()), alarm.getCron(), alarm.getStatus());
        }
    }
}
