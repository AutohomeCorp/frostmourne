package com.autohome.frostmourne.monitor.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.metric.jdbc.ClickhouseObjectMetric;
import com.autohome.frostmourne.monitor.service.core.metric.jdbc.MysqlObjectMetric;
import com.autohome.frostmourne.monitor.service.core.rule.BucketNumbericRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.autohome.frostmourne.monitor.service.core.metric.HttpMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.metric.elasticsearch.ElasticsearchNumericMetric;
import com.autohome.frostmourne.monitor.service.core.metric.elasticsearch.ElasticsearchSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.metric.influxdb.InfluxdbNumericMetric;
import com.autohome.frostmourne.monitor.service.core.metric.influxdb.InfluxdbSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.metric.jdbc.ClickhouseNumericMetric;
import com.autohome.frostmourne.monitor.service.core.metric.jdbc.ClickhouseSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.metric.jdbc.MysqlNumericMetric;
import com.autohome.frostmourne.monitor.service.core.metric.jdbc.MysqlSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.query.IInfluxdbDataQuery;
import com.autohome.frostmourne.monitor.service.core.rule.ExpressionRule;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;
import com.autohome.frostmourne.monitor.service.core.rule.NumericRule;
import com.autohome.frostmourne.monitor.service.core.rule.PercentageRule;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

@Configuration
public class CoreConfig {

    @Resource
    private ITemplateService templateService;

    @Autowired
    private MysqlNumericMetric mysqlNumericMetric;

    @Autowired
    private MysqlSameTimeMetric mysqlSameTimeMetric;

    @Autowired
    private MysqlObjectMetric mysqlObjectMetric;

    @Autowired
    private ClickhouseNumericMetric clickhouseNumericMetric;

    @Autowired
    private ClickhouseSameTimeMetric clickhouseSameTimeMetric;

    @Autowired
    private ClickhouseObjectMetric clickhouseObjectMetric;

    @Bean
    public Map<String, IRule> ruleMap() {
        Map<String, IRule> ruleMap = new HashMap<>();
        ruleMap.put("numeric", numericRule());
        ruleMap.put("expression", expressionRule());
        ruleMap.put("percentage", percentageRule());
        ruleMap.put("bucket_numeric", bucketNumbericRule());

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

    @Bean(name = "elasticsearchMetricMap")
    public Map<String, IMetric> elasticsearchMetricMap() {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("numeric", elasticsearchNumericMetric());
        metricMap.put("same_time", elasticsearchSameTimeMetric());
        metricMap.put("bucket_numeric", elasticsearchNumericMetric());
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

    @Bean(name = "influxdbMetricMap")
    public Map<String, IMetric> influxdbMetricMap(IInfluxdbDataQuery influxdbDataQuery) {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("numeric", new InfluxdbNumericMetric(influxdbDataQuery));
        metricMap.put("same_time", new InfluxdbSameTimeMetric(influxdbDataQuery));
        return metricMap;
    }

    @Bean(name = "mysqlMetricMap")
    public Map<String, IMetric> mysqlMetricMap() {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("numeric", mysqlNumericMetric);
        metricMap.put("same_time", mysqlSameTimeMetric);
        metricMap.put("object", mysqlObjectMetric);
        return metricMap;
    }

    @Bean(name = "clickhouseMetricMap")
    public Map<String, IMetric> clickhouseMetricMap() {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("numeric", clickhouseNumericMetric);
        metricMap.put("same_time", clickhouseSameTimeMetric);
        metricMap.put("object", clickhouseObjectMetric);
        return metricMap;
    }

}
