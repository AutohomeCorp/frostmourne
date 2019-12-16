package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RuleService implements IRuleService {

    @Resource
    private Map<String, IRule> ruleMap;

    public IRule findRule(String ruleType) {
        if (!ruleMap.containsKey(ruleType)) {
            throw new RuntimeException("unknown ruleType: " + ruleType);
        }

        return ruleMap.get(ruleType);
    }
}
