package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.domain.ReferenceBag;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;
import com.google.common.base.Strings;

public class PercentageRule extends AbstractRule {

    public PercentageRule(ITemplateService templateService) {
        super(templateService);
    }

    @Override
    public boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract,
        MetricContract metricContract, IMetric metric) {
        Map<String, Object> context = context(alarmProcessLogger, ruleContract, metricContract, metric);
        Map<String, String> ruleSettings = ruleContract.getSettings();
        List<ReferenceBag> referenceDataList = findReference(context);
        boolean verifyResult = true;
        for (ReferenceBag referenceBag : referenceDataList) {
            verifyResult = verifyResult && verify(context, referenceBag, ruleSettings);
        }
        return verifyResult;
    }

    protected Double findPercentageThreshold(Map<String, String> ruleSettings) {
        if (!ruleSettings.containsKey("PERCENTAGE_THRESHOLD")) {
            throw new RuntimeException("PercentageRule中percentage_threshold属性不存在。");
        }

        return Double.parseDouble(ruleSettings.get("PERCENTAGE_THRESHOLD"));
    }

    protected String findOperationType(Map<String, String> ruleSettings) {
        if (!ruleSettings.containsKey("COMPARE_TYPE")) {
            throw new RuntimeException("PercentageRule中COMPARE_TYPE属性不存在。");
        }

        return ruleSettings.get("COMPARE_TYPE");
    }

    protected Double findDiffThreshold(Map<String, String> ruleSettings) {
        if (!ruleSettings.containsKey("DIFF_VALUE_THRESHOLD")) {
            return 0D;
        }

        return Double.parseDouble(ruleSettings.get("DIFF_VALUE_THRESHOLD"));
    }

    protected List<ReferenceBag> findReference(Map<String, Object> context) {
        return (List<ReferenceBag>)context.get("REFERENCE_LIST");
    }

    protected Double findCurrent(Map<String, Object> context) {
        return (Double)context.get("CURRENT");
    }

    protected Double findDiff(Map<String, Object> context, ReferenceBag referenceBag) {
        return findCurrent(context) - referenceBag.getValue();
    }

    protected String findDiffCompareOp(Map<String, String> ruleSettings) {
        if (!ruleSettings.containsKey("DIFF_COMPARE_TYPE")) {
            return null;
        }
        return ruleSettings.get("DIFF_COMPARE_TYPE");
    }

    private boolean verify(Map<String, Object> context, ReferenceBag referenceBag, Map<String, String> ruleSettings) {
        return verifyPercentage(referenceBag, ruleSettings) && verifyDiff(context, referenceBag, ruleSettings);
    }

    boolean verifyPercentage(ReferenceBag referenceBag, Map<String, String> ruleSettings) {
        Double percentage = referenceBag.getPercentage();
        Double percentageThreshold = findPercentageThreshold(ruleSettings);
        String operationType = findOperationType(ruleSettings);
        if ("INCREASE".equalsIgnoreCase(operationType)) {
            return percentage >= percentageThreshold;
        } else if ("DECREASE".equalsIgnoreCase(operationType)) {
            return percentage <= -percentageThreshold;
        } else if ("BOTH".equalsIgnoreCase(operationType)) {
            return Math.abs(percentage) >= percentageThreshold;
        } else {
            throw new IllegalArgumentException("unknown operation_type: " + operationType);
        }
    }

    boolean verifyDiff(Map<String, Object> context, ReferenceBag referenceBag, Map<String, String> ruleSettings) {
        String diffCompareOp = findDiffCompareOp(ruleSettings);
        if (Strings.isNullOrEmpty(diffCompareOp)) {
            return true;
        }

        Double diff = findDiff(context, referenceBag);
        Double diffThreshold = findDiffThreshold(ruleSettings);
        if ("gte".equalsIgnoreCase(diffCompareOp)) {
            return diff >= diffThreshold;
        } else if ("lte".equalsIgnoreCase(diffCompareOp)) {
            return diff <= diffThreshold;
        } else if ("abs_gte".equalsIgnoreCase(diffCompareOp)) {
            return Math.abs(diff) >= diffThreshold;
        } else if ("abs_lte".equalsIgnoreCase(diffCompareOp)) {
            return Math.abs(diff) <= diffThreshold;
        }
        throw new IllegalArgumentException("unknown diff operation:" + diffCompareOp);
    }
}
