package com.autohome.frostmourne.monitor.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.metric.ElasticsearchNumericMetric;
import com.autohome.frostmourne.monitor.service.core.metric.ElasticsearchSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.metric.HttpMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.rule.ExpressionRule;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;
import com.autohome.frostmourne.monitor.service.core.rule.NumericRule;
import com.autohome.frostmourne.monitor.service.core.rule.PercentageRule;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Map<String, IMetric> elasticsearchMetricMap() {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("numeric", elasticsearchNumericMetric());
        metricMap.put("same_time", elasticsearchSameTimeMetric());
        return metricMap;
    }

    @Bean
    public ElasticsearchNumericMetric elasticsearchNumericMetric() {
        return new ElasticsearchNumericMetric();
    }

    @Bean
    public ElasticsearchSameTimeMetric elasticsearchSameTimeMetric() {
        return new ElasticsearchSameTimeMetric();
    }

    @Bean
    public HttpMetric httpMetric() {
        return new HttpMetric();
    }
}
