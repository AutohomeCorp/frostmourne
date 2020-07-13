package com.autohome.frostmourne.monitor.controller.account;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.service.account.ITeamInfoService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/team", "/api/monitor-api/team"})
public class TeamInfoController {

    @Resource
    private ITeamInfoService teamInfoService;

    @PostMapping(value = "/create")
    public Protocol<Boolean> create(@RequestBody TeamInfo teamInfo) {
        return new Protocol<>(teamInfoService.insert(teamInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/update")
    public Protocol<Boolean> update(@RequestBody TeamInfo teamInfo) {
        return new Protocol<>(teamInfoService.update(teamInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/delete")
    public Protocol<Boolean> delete(@RequestParam(value = "id", required = true) Long id) {
        return new Protocol<>(teamInfoService.delete(id));
    }

    @GetMapping(value = "/findPage")
    public Protocol<PagerContract<TeamInfo>> findPage(@RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                      @RequestParam(value = "pageSize", required = true) int pageSize,
                                                      @RequestParam(value = "id", required = false) Long id,
                                                      @RequestParam(value = "teamName", required = false) String teamName) {
        return new Protocol<>(teamInfoService.findPage(pageIndex, pageSize, id, teamName));
    }

    @GetMapping(value = "/find")
    public Protocol<List<TeamInfo>> find() {
        return new Protocol<>(teamInfoService.find(null));
    }
}
