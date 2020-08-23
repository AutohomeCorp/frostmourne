package com.autohome.frostmourne.monitor.controller;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.ServerInfoContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoQueryForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSaveForm;
import com.autohome.frostmourne.monitor.service.core.server.IServerInfoService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/serverinfo"})
public class ServerInfoController {

    @Resource
    private IServerInfoService serverInfoService;

    @PostMapping("/save")
    public Protocol<Object> save(@RequestParam("_appId") String appId,
                                 @RequestBody ServerInfoSaveForm form) {
        String account = AuthTool.currentUser().getAccount();
        serverInfoService.save(form, account);
        return new Protocol<>(0, "成功");
    }

    @GetMapping("/get")
    public Protocol<ServerInfoContract> getContract(@RequestParam("_appId") String appId,
                                                    @RequestParam("id") Long id) {
        Optional<ServerInfoContract> result = serverInfoService.getContract(id);
        return result.map(data -> new Protocol<>(data, 0, "成功"))
                .orElseGet(() -> new Protocol<>(-1, "不存在"));
    }

    @RequestMapping("/find")
    public Protocol<PagerContract<ServerInfoContract>> findContract(@RequestParam("_appId") String appId,
                                                                    @ModelAttribute ServerInfoQueryForm form) {
        PagerContract<ServerInfoContract> result = serverInfoService.findContract(form);
        return new Protocol<>(result, 0, "成功");
    }

}
