package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.DataNameMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.DataSourceMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.MetricMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.RecipientMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.RuleMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.RulePropertyMapper;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.admin.IScheduleService;
import com.autohome.frostmourne.monitor.transform.DataNameTransformer;
import com.autohome.frostmourne.monitor.transform.DataSourceTransformer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    private AlarmMapper alarmMapper;

    @Resource
    private RecipientMapper recipientMapper;

    @Resource(name = "frostmourneTransactionManager")
    private DataSourceTransactionManager frostmourneTransactionManager;

    @Resource
    private AlertMapper alertMapper;

    @Resource
    private RulePropertyMapper rulePropertyMapper;

    @Resource
    private RuleMapper ruleMapper;

    @Resource
    private MetricMapper metricMapper;

    @Resource
    private DataSourceMapper dataSourceMapper;

    @Resource
    private DataNameMapper dataNameMapper;

    @Resource
    private IScheduleService scheduleService;

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
        Alarm alarm = alarmMapper.selectByPrimaryKey(alarmId);
        if (alarm == null) {
            return false;
        }
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = frostmourneTransactionManager.getTransaction(def);
        try {
            alarmMapper.deleteByPrimaryKey(alarmId);
            alertMapper.deleteByAlarm(alarmId);
            metricMapper.deleteByAlarm(alarmId);
            ruleMapper.deleteByAlarm(alarmId);
            rulePropertyMapper.deleteByAlarm(alarmId);
            recipientMapper.deleteByAlarm(alarmId);
            scheduleService.removeJob(Math.toIntExact(alarm.getJob_id()));
        } catch (Exception ex) {
            frostmourneTransactionManager.rollback(status);
        }
        frostmourneTransactionManager.commit(status);
        return true;
    }

    @Transactional(value = "frostmourneTransactionManager")
    public boolean open(Long alarmId) {
        boolean result = updateStatus(alarmId, AlarmStatus.OPEN);
        Alarm alarm = this.alarmMapper.selectByPrimaryKey(alarmId);
        this.scheduleService.openJob(Math.toIntExact(alarm.getJob_id()));
        return result;
    }

    @Transactional(value = "frostmourneTransactionManager")
    public boolean close(Long alarmId) {
        boolean result = updateStatus(alarmId, AlarmStatus.CLOSE);
        Alarm alarm = this.alarmMapper.selectByPrimaryKey(alarmId);
        this.scheduleService.closeJob(Math.toIntExact(alarm.getJob_id()));
        return result;
    }

    public AlarmContract findById(Long alarmId) {
        AlarmContract alarmContract = new AlarmContract();
        Alarm alarm = alarmMapper.selectByPrimaryKey(alarmId);
        if (alarm == null) {
            return null;
        }
        alarmContract.setId(alarmId);
        alarmContract.setStatus(alarm.getStatus());
        alarmContract.setOwner_key(alarm.getOwner_key());
        alarmContract.setDescription(alarm.getDescription());
        alarmContract.setCron(alarm.getCron());
        alarmContract.setTeam_name(alarm.getTeam_name());
        alarmContract.setAlarm_type(alarm.getAlarm_type());
        alarmContract.setAlarm_name(alarm.getAlarm_name());
        alarmContract.setExecute_result(alarm.getExecute_result());
        alarmContract.setExecute_at(alarm.getExecute_at());
        alarmContract.setJob_id(alarm.getJob_id());

        MetricContract metricContract = new MetricContract();
        Metric metric = this.metricMapper.findOneByAlarm(alarmId);
        metricContract.setAggregation_type(metric.getAggregation_type());
        metricContract.setAggregation_field(metric.getAggregation_field());
        metricContract.setQuery_string(metric.getQuery_string());
        metricContract.setData_source_id(metric.getData_source_id());
        metricContract.setData_name(metric.getData_name());
        metricContract.setMetric_type(metric.getMetric_type());
        metricContract.setData_name_id(metric.getData_name_id());
        metricContract.setAlarm_id(alarmId);
        metricContract.setPost_data(metric.getPost_data());
        metricContract.setProperties(JacksonUtil.deSerialize(metric.getProperties(), new TypeReference<Map<String, Object>>() {
        }));
        if (metric.getData_source_id() != null && metric.getData_source_id() > 0) {
            DataSource dataSource = dataSourceMapper.selectByPrimaryKey(metric.getData_source_id());
            DataSourceContract dataSourceContract = DataSourceTransformer.model2Contract(dataSource);
            metricContract.setDataSourceContract(dataSourceContract);
        }

        if (metric.getData_name_id() != null && metric.getData_name_id() > 0) {
            DataName dataName = dataNameMapper.selectByPrimaryKey(metric.getData_name_id());
            DataNameContract dataNameContract = DataNameTransformer.model2Contract(dataName);
            metricContract.setDataNameContract(dataNameContract);
        }

        alarmContract.setMetricContract(metricContract);

        RuleContract ruleContract = new RuleContract();
        Rule rule = this.ruleMapper.findOneByAlarm(alarmId);
        ruleContract.setAlert_template(rule.getAlert_template());
        ruleContract.setRule_type(rule.getRule_type());
        ruleContract.setAlarm_id(alarmId);
        Map<String, String> rulePropertyMap = new HashMap<>();

        List<RuleProperty> rulePropertyList = this.rulePropertyMapper.findByRuleId(rule.getId());
        if (rulePropertyList != null && rulePropertyList.size() > 0) {
            for (RuleProperty ruleProperty : rulePropertyList) {
                rulePropertyMap.put(ruleProperty.getProp_key(), ruleProperty.getProp_value());
            }
        }
        ruleContract.setSettings(rulePropertyMap);
        alarmContract.setRuleContract(ruleContract);

        AlertContract alertContract = new AlertContract();
        Alert alert = this.alertMapper.findOneByAlarm(alarmId);
        alertContract.setSilence(alert.getSilence());
        alertContract.setWays(Splitter.on(",").splitToList(alert.getWays()));
        alertContract.setAlarm_id(alarmId);
        alertContract.setAllow_sms_from(alert.getAllow_sms_from());
        alertContract.setAllow_sms_to(alert.getAllow_sms_to());
        alertContract.setDing_robot_hook(alert.getDing_robot_hook());
        alertContract.setHttp_post_url(alert.getHttp_post_url());
        alertContract.setWechat_robot_hook(alert.getWechat_robot_hook());
        alertContract.setCreate_at(alert.getCreate_at());

        List<Recipient> recipientList = this.recipientMapper.findByAlarm(alarmId);
        alertContract.setRecipients(recipientList.stream().map(Recipient::getAccount).collect(Collectors.toList()));
        alarmContract.setAlertContract(alertContract);
        return alarmContract;
    }

    public PagerContract<Alarm> find(int pageIndex, int pageSize, Long alarmId, String name,
                                     String teamName, String status) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<Alarm> list = this.alarmMapper.find(alarmId, name, teamName, status);
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int) page.getTotal());
    }


    @Override
    public void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status) {
        alarmMapper.updateAlarmLastExecuteInfo(alarmId, executeTime, status.getName());
    }

    private boolean updateStatus(Long alarmId, String status) {
        return alarmMapper.updateStatus(alarmId, status) > 0;
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

        alarm.setAlarm_name(alarmContract.getAlarm_name());
        alarm.setAlarm_type(alarmContract.getAlarm_type());
        alarm.setCreator(alarmContract.getOperator());
        alarm.setCron(alarmContract.getCron());
        alarm.setTeam_name(alarmContract.getTeam_name());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setModifier(alarmContract.getOperator());
        alarm.setOwner_key(alarmContract.getOwner_key());
        alarm.setStatus(alarmContract.getStatus());
        Date now = new Date();
        alarm.setCreate_at(now);
        alarm.setModify_at(now);
        alarm.setJob_id(0L);
        alarm.setExecute_result(ExecuteStatus.WAITING.getName());
        alarmMapper.insert(alarm);

        return alarm;
    }

    private Alarm updateAlarm(AlarmContract alarmContract) {
        Date now = new Date();

        Alarm alarm = new Alarm();
        alarm.setId(alarmContract.getId());
        alarm.setAlarm_name(alarmContract.getAlarm_name());
        alarm.setAlarm_type(alarmContract.getAlarm_type());
        alarm.setDescription(alarmContract.getDescription());
        alarm.setOwner_key(alarmContract.getOwner_key());
        alarm.setStatus(alarmContract.getStatus());
        alarm.setCron(alarmContract.getCron());
        alarm.setModify_at(now);
        alarm.setModifier(alarmContract.getOperator());

        alarmMapper.updateByPrimaryKeySelective(alarm);

        alarm = alarmMapper.selectByPrimaryKey(alarmContract.getId());
        return alarm;
    }

    private void saveAlert(AlertContract contract, Long alarmId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            recipientMapper.deleteByAlarm(alarmId);
            alertMapper.deleteByAlarm(alarmId);
        }
        Alert alert = new Alert();
        alert.setWays(String.join(",", contract.getWays()));
        alert.setAlarm_id(alarmId);
        alert.setSilence(contract.getSilence());
        alert.setAllow_sms_from(contract.getAllow_sms_from());
        alert.setAllow_sms_to(contract.getAllow_sms_to());
        alert.setCreator(account);
        alert.setCreate_at(new Date());
        alert.setDing_robot_hook(contract.getDing_robot_hook());
        alert.setHttp_post_url(contract.getHttp_post_url());
        alert.setWechat_robot_hook(contract.getWechat_robot_hook());
        alertMapper.insert(alert);

        for (String recipient : contract.getRecipients()) {
            Recipient alertRecipient = new Recipient();
            alertRecipient.setAlarm_id(alarmId);
            alertRecipient.setAlert_id(alert.getId());
            alertRecipient.setAccount(recipient);
            alertRecipient.setCreate_at(new Date());
            recipientMapper.insert(alertRecipient);
        }
    }

    private Long saveRule(RuleContract ruleContract, Long alarmId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            rulePropertyMapper.deleteByAlarm(alarmId);
            ruleMapper.deleteByAlarm(alarmId);
        }

        Rule rule = new Rule();
        rule.setAlarm_id(alarmId);
        rule.setAlert_template(ruleContract.getAlert_template());
        rule.setCreator(account);
        rule.setCreate_at(new Date());
        rule.setRule_type(ruleContract.getRule_type());
        ruleMapper.insert(rule);
        Long ruleId = rule.getId();
        if (ruleContract.getSettings() != null) {
            for (Map.Entry<String, String> entry : ruleContract.getSettings().entrySet()) {
                RuleProperty ruleProperty = new RuleProperty();
                ruleProperty.setAlarm_id(alarmId);
                ruleProperty.setCreate_at(new Date());
                ruleProperty.setProp_key(entry.getKey());
                ruleProperty.setProp_value(entry.getValue());
                ruleProperty.setRule_id(ruleId);
                ruleProperty.setCreator(account);
                rulePropertyMapper.insert(ruleProperty);
            }
        }

        return ruleId;
    }

    private void saveMetric(MetricContract metricContract, Long alarmId, Long ruleId, boolean isNewAlarm, String account) {
        if (!isNewAlarm) {
            metricMapper.deleteByAlarm(alarmId);
        }

        Metric metric = new Metric();
        metric.setAlarm_id(alarmId);
        metric.setCreator(account);
        metric.setRule_id(ruleId);
        metric.setAggregation_type(metricContract.getAggregation_type());
        metric.setAggregation_field(metricContract.getAggregation_field());
        metric.setData_name(metricContract.getData_name());
        metric.setData_source_id(metricContract.getData_source_id());
        metric.setData_name_id(metricContract.getData_name_id());
        metric.setMetric_type(metricContract.getMetric_type());
        metric.setQuery_string(metricContract.getQuery_string());
        metric.setPost_data(metricContract.getPost_data());
        metric.setProperties(JacksonUtil.serialize(metricContract.getProperties()));
        metric.setCreate_at(new Date());
        metricMapper.insert(metric);
    }

    public void padAlarm(AlarmContract alarmContract) {
        alarmContract.setAlarm_type(alarmContract.getMetricContract().getData_name());
        String ruleType = metricRuleMap.get(alarmContract.getMetricContract().getMetric_type());
        alarmContract.getRuleContract().setRule_type(ruleType);

        if (!alarmContract.getMetricContract().getData_name().equalsIgnoreCase("http")) {
            DataName dataName = dataNameMapper.findByName(alarmContract.getMetricContract().getData_name());
            alarmContract.getMetricContract().setData_name_id(dataName.getId());
            alarmContract.getMetricContract().setDataNameContract(DataAdminService.toDataNameContract(dataName));
            alarmContract.getMetricContract().setData_source_id(dataName.getData_source_id());

            DataSource dataSource = dataSourceMapper.selectByPrimaryKey(dataName.getData_source_id());
            alarmContract.getMetricContract().setDataSourceContract(DataSourceTransformer.model2Contract(dataSource));
        }
    }

    private void saveJobSchedule(boolean isNewAlarm, Alarm alarm) {
        if (isNewAlarm) {
            Integer jobId = this.scheduleService.addJob(alarm.getId(), alarm.getCron(), alarm.getStatus());
            alarmMapper.updateJobId(alarm.getId(), new Long(jobId));
        } else {
            this.scheduleService.updateJob(alarm.getId(), Math.toIntExact(alarm.getJob_id()), alarm.getCron(), alarm.getStatus());
        }
    }
}
