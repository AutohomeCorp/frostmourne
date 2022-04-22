package com.autohome.frostmourne.monitor.controller;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.model.contract.ServiceInfoContract;
import com.autohome.frostmourne.monitor.model.contract.ServiceInfoQueryForm;
import com.autohome.frostmourne.monitor.model.contract.ServiceInfoSaveForm;
import com.autohome.frostmourne.monitor.service.core.service.IServiceInfoService;
import com.autohome.frostmourne.monitor.tool.AuthTool;

@RestController
@RequestMapping(value = {"/serviceinfo", "/api/monitor-api/serviceinfo"})
public class ServiceInfoController {

    @Resource
    private IServiceInfoService serviceInfoService;

    @PostMapping("/save")
    public Protocol<Object> save(@RequestBody ServiceInfoSaveForm form) {
        String account = AuthTool.currentUser().getAccount();
        serviceInfoService.save(form, account);
        return new Protocol<>(0, "成功");
    }

    @GetMapping("/get")
    public Protocol<ServiceInfoContract> getContract(@RequestParam("id") Long id) {
        Optional<ServiceInfoContract> result = serviceInfoService.getContract(id);
        return result.map(data -> new Protocol<>(data, 0, "成功")).orElseGet(() -> new Protocol<>(-1, "不存在"));
    }

    @RequestMapping("/find")
    public Protocol<PagerContract<ServiceInfoContract>> findContract(@ModelAttribute ServiceInfoQueryForm form) {
        PagerContract<ServiceInfoContract> result = serviceInfoService.findContract(form);
        return new Protocol<>(result, 0, "成功");
    }

}
