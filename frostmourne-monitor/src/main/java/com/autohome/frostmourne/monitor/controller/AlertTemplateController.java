package com.autohome.frostmourne.monitor.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateContract;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.model.contract.TreeDataOption;
import com.autohome.frostmourne.monitor.service.core.template.IAlertTemplateService;
import com.autohome.frostmourne.monitor.tool.AuthTool;

@RestController
@RequestMapping(value = {"/alerttemplate", "/api/monitor-api/alerttemplate"})
public class AlertTemplateController {

    @Resource
    private IAlertTemplateService alertTemplateService;

    @PostMapping("/save")
    public Protocol<Object> save(@RequestBody AlertTemplateSaveForm form) {
        String account = AuthTool.currentUser().getAccount();
        alertTemplateService.save(form, account);
        return new Protocol<>(0, "成功");
    }

    @GetMapping("/get")
    public Protocol<AlertTemplateContract> getContract(@RequestParam("id") Long id) {
        Optional<AlertTemplateContract> result = alertTemplateService.getContract(id);
        return result.map(data -> new Protocol<>(data, 0, "成功")).orElseGet(() -> new Protocol<>(-1, "不存在"));
    }

    @RequestMapping("/find")
    public Protocol<PagerContract<AlertTemplateContract>> findContract(@ModelAttribute AlertTemplateQueryForm form) {
        PagerContract<AlertTemplateContract> result = alertTemplateService.findContract(form);
        return new Protocol<>(result, 0, "成功");
    }

    @RequestMapping("/templatetypeoption/list")
    public Protocol<List<TreeDataOption>> listTemplateTypeOptions() {
        List<TreeDataOption> result = alertTemplateService.listTemplateTypeOptions();
        return new Protocol<>(result);
    }

}
