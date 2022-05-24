package com.autohome.frostmourne.monitor.controller;

import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataOption;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.autohome.frostmourne.monitor.tool.AuthTool;

@RestController
@RequestMapping(value = {"/data", "/api/monitor-api/data"})
public class DataNameController {

    @Resource
    private IDataAdminService dataAdminService;

    @RequestMapping(value = "/saveDataSource", method = RequestMethod.POST)
    public Protocol saveDataSource(@RequestBody DataSourceContract dataSource) {
        String account = AuthTool.currentUser().getAccount();
        boolean result = dataAdminService.saveDataSource(account, dataSource);
        if (result) {
            return new Protocol(0, "保存成功");
        }
        return new Protocol(101, "保存失败, 请检查地址和认证信息是否正确");
    }

    @RequestMapping(value = "/removeDataSource", method = RequestMethod.POST)
    public Protocol<Boolean> removeDataSource(@RequestParam(value = "id") Long id) {
        boolean success = dataAdminService.removeDataSource(id);
        return new Protocol<>(success);
    }

    @RequestMapping(value = "/findDataSource", method = RequestMethod.GET)
    public Protocol<PagerContract<DataSourceContract>> findDataSource(@RequestParam(value = "pageIndex") int pageIndex,
        @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "datasourceType", required = false) DataSourceType datasourceType) {
        PagerContract<DataSourceContract> pagerContract = dataAdminService.findDatasource(pageIndex, pageSize, datasourceType);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findDataSourceByType", method = RequestMethod.GET)
    public Protocol<List<DataSource>> findDataSourceByType(@RequestParam(value = "datasourceType") DataSourceType datasourceType) {
        List<DataSource> dataSources = this.dataAdminService.findDataSourceByType(datasourceType);
        return new Protocol<>(dataSources);
    }

    @RequestMapping(value = "/saveDataName", method = RequestMethod.POST)
    public Protocol saveDataName(@RequestBody DataNameContract dataNameContract) {
        String account = AuthTool.currentUser().getAccount();
        boolean result = dataAdminService.saveDataName(account, dataNameContract);
        if (result) {
            return new Protocol(0, "保存成功");
        }
        return new Protocol(101, "保存失败");
    }

    @RequestMapping(value = "/removeDataName", method = RequestMethod.POST)
    public Protocol<Boolean> removeDataName(@RequestParam(value = "id") Long id) {
        boolean success = dataAdminService.removeDataName(id);
        return new Protocol<>(success);
    }

    @RequestMapping(value = "/findDataName", method = RequestMethod.GET)
    public Protocol<PagerContract<DataNameContract>> findDataName(@RequestParam(value = "pageIndex") int pageIndex,
        @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "datasourceType", required = false) DataSourceType datasourceType,
        @RequestParam(value = "datasourceId", required = false) Long datasourceId, @RequestParam(value = "nameHint", required = false) String nameHint) {
        PagerContract<DataNameContract> pagerContract = dataAdminService.findDataName(pageIndex, pageSize, datasourceType, datasourceId);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findDataNameByType", method = RequestMethod.GET)
    public Protocol<List<DataNameContract>> findDataNameByType(@RequestParam(value = "datasourceType") DataSourceType datasourceType) {
        return new Protocol<>(dataAdminService.findDataNameByType(datasourceType));
    }

    @RequestMapping(value = "/dataOptions", method = RequestMethod.GET)
    public Protocol<List<DataOption>> dataOptions() {
        return new Protocol<>(this.dataAdminService.dataOptions());
    }
}
