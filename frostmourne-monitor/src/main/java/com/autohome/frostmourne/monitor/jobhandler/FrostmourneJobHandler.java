package com.autohome.frostmourne.monitor.jobhandler;

import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonObjectMapper;
import com.autohome.frostmourne.monitor.service.core.execute.IAlarmService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 任务Handler示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHandler(value = "frostmourneJobHandler")
@Component
public class FrostmourneJobHandler extends IJobHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrostmourneJobHandler.class);

    @Resource
    private IAlarmService alarmService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
            };
            Map<String, Object> paramMap = JacksonObjectMapper.getCommonObjectMapper().readValue(param, typeRef);
            Long alarmId = new Long(paramMap.get("alarmId").toString());
            alarmService.run("system", alarmId, false);
            XxlJobLogger.log("frostmourne, job begin.");
            return SUCCESS;
        } catch (Exception ex) {
            LOGGER.error("error when execute, param: " + param, ex);
            return FAIL;
        }
    }

}
