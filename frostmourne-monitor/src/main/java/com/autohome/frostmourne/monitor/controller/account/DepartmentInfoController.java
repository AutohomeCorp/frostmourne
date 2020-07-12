package com.autohome.frostmourne.monitor.controller.account;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DepartmentInfo;
import com.autohome.frostmourne.monitor.service.account.IDepartmentInfoService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/department", "/api/monitor-api/department"})
public class DepartmentInfoController {

    @Resource
    private IDepartmentInfoService departmentInfoService;

    @PostMapping(value = "/create")
    public Protocol<Boolean> create(@RequestBody DepartmentInfo departmentInfo) {
        return new Protocol<>(departmentInfoService.insert(departmentInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/update")
    public Protocol<Boolean> update(@RequestBody DepartmentInfo departmentInfo) {
        return new Protocol<>(departmentInfoService.update(departmentInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/delete")
    public Protocol<Boolean> delete(@RequestParam(value = "id", required = true) Long id) {
        return new Protocol<>(departmentInfoService.delete(id));
    }

    @GetMapping(value = "/findPage")
    public Protocol<PagerContract<DepartmentInfo>> findPage(@RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                            @RequestParam(value = "pageSize", required = true) int pageSize,
                                                            @RequestParam(value = "id", required = false) Long id,
                                                            @RequestParam(value = "account", required = false) String account) {
        return new Protocol<>(departmentInfoService.findPage(pageIndex, pageSize, id, account));
    }

    @GetMapping(value = "/find")
    public Protocol<List<DepartmentInfo>> find() {
        return new Protocol<>(departmentInfoService.find());
    }
}
