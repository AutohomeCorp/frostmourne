package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IOrgPlugin;
import com.autohome.frostmourne.spi.service.impl.FileUserService;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.google.common.base.Strings;

public class DefaultOrgPlugin implements IOrgPlugin {

    @Resource
    private FileUserService fileUserService;

    @Override
    public List<Department> departments() {
        return fileUserService.departments();
    }

    @Override
    public List<Team> teams(String department) {
        return fileUserService.teams(department);
    }
}
