package com.autohome.frostmourne.monitor.controller;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.AlertTemplateContract;
import com.autohome.frostmourne.monitor.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.contract.TreeDataOption;
import com.autohome.frostmourne.monitor.service.core.template.IAlertTemplateService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerttemplate")
public class AlertTemplateController {

    @Resource
    private IAlertTemplateService alertTemplateService;

    @PostMapping("/save")
    public Protocol<Object> save(@RequestParam("_appId") String appId,
                                 @RequestBody AlertTemplateSaveForm form) {
        String account = AuthTool.currentUser().getAccount();
        alertTemplateService.save(form, account);
        return new Protocol<>(0, "成功");
    }

    @GetMapping("/get")
    public Protocol<AlertTemplateContract> getContract(@RequestParam("_appId") String appId,
                                                       @RequestParam("id") Long id) {
        Optional<AlertTemplateContract> result = alertTemplateService.getContract(id);
        return result.map(data -> new Protocol<>(data, 0, "成功"))
                .orElseGet(() -> new Protocol<>(-1, "不存在"));
    }

    @RequestMapping("/find")
    public Protocol<PagerContract<AlertTemplateContract>> findContract(@RequestParam("_appId") String appId,
                                                                       @ModelAttribute AlertTemplateQueryForm form) {
        PagerContract<AlertTemplateContract> result = alertTemplateService.findContract(form);
        return new Protocol<>(result, 0, "成功");
    }

    @RequestMapping("/templatetypeoption/list")
    public Protocol<List<TreeDataOption>> listTemplateTypeOptions(@RequestParam("_appId") String appId) {
        List<TreeDataOption> result = alertTemplateService.listTemplateTypeOptions();
        return new Protocol<>(result);
    }

}
