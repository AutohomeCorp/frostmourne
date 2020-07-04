package com.autohome.frostmourne.spi.plugin.autohome;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IOrgPlugin;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.google.common.base.Strings;

public class AutohomeOrgPlugin implements IOrgPlugin {

    @Resource
    private Map<String, Department> departmentMap;

    @Resource
    private Map<String, Team> teamMap;

    @Override
    public List<Department> departments() {
        return departmentMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public List<Team> teams(String department) {
        if (Strings.isNullOrEmpty(department)) {
            return teamMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        }
        return teamMap.entrySet().stream().filter(entrySet -> entrySet.getValue().getDepartment().equalsIgnoreCase(department))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
