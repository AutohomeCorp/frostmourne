package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.domain.BucketInfo;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/3 22:31
 */
public class BucketNumbericRule extends AbstractRule {

    public BucketNumbericRule(ITemplateService templateService) {
        super(templateService);
    }

    @Override
    public boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric) {
        Map<String, Object> context = context(alarmProcessLogger, ruleContract, metricContract, metric);
        List<BucketInfo> buckets = findBuckets(context);
        List<BucketInfo> verifiedBuckets = new ArrayList<>();
        Double threshold = findThreshold(ruleContract);
        String operator = findOperation(ruleContract);
        for(BucketInfo bucketInfo: buckets) {
            Double number = Double.parseDouble(bucketInfo.getValue().toString());
            if(compare(number, threshold, operator)) {
                verifiedBuckets.add(bucketInfo);
            }
        }
        alarmProcessLogger.getContext().put("VERIFIED_BUCKETS", verifiedBuckets);
        alarmProcessLogger.trace("VERIFIED_BUCKETS = %s", JacksonUtil.serialize(verifiedBuckets));
        return verifiedBuckets.size() > 0;
    }

    private List<BucketInfo> findBuckets(Map<String, Object> context) {
        if(!context.containsKey("BUCKETS")) {
            throw new RuntimeException("BucketNumbericRule缺少Buckets数据");
        }
        return (List<BucketInfo>) context.get("BUCKETS");
    }

    private Double findThreshold(RuleContract ruleContract) {
        if (!ruleContract.getSettings().containsKey("THRESHOLD")) {
            throw new RuntimeException("BucketNumbericRule中THRESHOLD属性不存在。");
        }
        return Double.parseDouble(ruleContract.getSettings().get("THRESHOLD"));
    }

    private String findOperation(RuleContract ruleContract) {
        if (!ruleContract.getSettings().containsKey("OPERATOR")) {
            throw new RuntimeException("BucketNumbericRule中OPERATOR属性不存在。");
        }
        return ruleContract.getSettings().get("OPERATOR");
    }

    private boolean compare(Double number, Double threshold, String operator) {
        if ("gte".equalsIgnoreCase(operator)) {
            return number >= threshold;
        }
        if ("lte".equalsIgnoreCase(operator)) {
            return number <= threshold;
        }
        if ("equal".equalsIgnoreCase(operator)) {
            return number.equals(threshold);
        }

        throw new RuntimeException("unknown operator: " + operator);
    }
}
