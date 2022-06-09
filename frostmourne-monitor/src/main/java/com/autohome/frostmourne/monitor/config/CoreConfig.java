package com.autohome.frostmourne.monitor.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.rule.BucketNumbericRule;
import com.autohome.frostmourne.monitor.service.core.rule.PingRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.autohome.frostmourne.monitor.service.core.rule.ExpressionRule;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;
import com.autohome.frostmourne.monitor.service.core.rule.NumericRule;
import com.autohome.frostmourne.monitor.service.core.rule.PercentageRule;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

@Configuration
public class CoreConfig {

    @Resource
    private ITemplateService templateService;

    @Bean
    public Map<String, IRule> ruleMap() {
        Map<String, IRule> ruleMap = new HashMap<>();
        ruleMap.put("numeric", numericRule());
        ruleMap.put("expression", expressionRule());
        ruleMap.put("percentage", percentageRule());
        ruleMap.put("bucket_numeric", bucketNumbericRule());
        ruleMap.put("ping", pingRule());

        return ruleMap;
    }

    @Bean
    public NumericRule numericRule() {
        return new NumericRule(templateService);
    }

    @Bean
    public PercentageRule percentageRule() {
        return new PercentageRule(templateService);
    }

    @Bean
    public ExpressionRule expressionRule() {
        return new ExpressionRule(templateService);
    }

    @Bean
    public BucketNumbericRule bucketNumbericRule() {
        return new BucketNumbericRule(templateService);
    }

    @Bean
    public PingRule pingRule() {
        return new PingRule(templateService);
    }

}
