package com.autohome.frostmourne.spi.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.plugin.IOrgPlugin;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org")
public class OrgController {

    @Resource
    private IOrgPlugin orgService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public Protocol<List<Department>> departments(@RequestParam(name = "_appId", required = true) String _appId) {
        List<Department> departments = orgService.departments();
        return new Protocol<>(departments);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Protocol<List<Team>> teams(@RequestParam(name = "_appId", required = true) String _appId,
                                      @RequestParam(name = "department", required = false) String department) {
        List<Team> teams = orgService.teams(department);
        return new Protocol<>(teams);
    }
}
