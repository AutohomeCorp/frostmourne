package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.RuleContract;
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
    public boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric) {
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
        return (List<ReferenceBag>) context.get("REFERENCE_LIST");
    }

    protected Double findCurrent(Map<String, Object> context) {
        return (Double) context.get("CURRENT");
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

    private Double calculatePercentage(Map<String, Object> context, Double reference) {
        Double current = findCurrent(context);
        if (reference == 0) {
            return (current - reference) * 100 / (reference + 1);
        }
        return (current - reference) * 100 / reference;
    }

    private boolean verify(Map<String, Object> context, ReferenceBag referenceBag, Map<String, String> ruleSettings) {
        return verifyPercentage(context, referenceBag, ruleSettings) && verifyDiff(context, referenceBag, ruleSettings);
    }

    boolean verifyPercentage(Map<String, Object> context, ReferenceBag referenceBag, Map<String, String> ruleSettings) {
        Double percentage = calculatePercentage(context, referenceBag.getValue());
        String operationType = findOperationType(ruleSettings);
        Double percentageThreshold = findPercentageThreshold(ruleSettings);
        if (operationType.equalsIgnoreCase("INCREASE")) {
            return percentage >= percentageThreshold;
        } else if (operationType.equalsIgnoreCase("DECREASE")) {
            return percentage <= -percentageThreshold;
        } else if (operationType.equalsIgnoreCase("BOTH")) {
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
        if (diffCompareOp.equalsIgnoreCase("gte")) {
            return diff >= diffThreshold;
        } else if (diffCompareOp.equalsIgnoreCase("lte")) {
            return diff <= diffThreshold;
        } else if (diffCompareOp.equalsIgnoreCase("abs_gte")) {
            return Math.abs(diff) >= diffThreshold;
        } else if (diffCompareOp.equalsIgnoreCase("abs_lte")) {
            return Math.abs(diff) <= diffThreshold;
        }
        throw new IllegalArgumentException("unknown diff operation:" + diffCompareOp);
    }
}
