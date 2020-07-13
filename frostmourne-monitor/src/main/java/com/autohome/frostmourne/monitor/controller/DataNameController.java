package com.autohome.frostmourne.monitor.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataOption;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/data", "/api/monitor-api/data"})
public class DataNameController {

    @Resource
    private IDataAdminService dataAdminService;

    @RequestMapping(value = "/saveDataSource", method = RequestMethod.POST)
    public Protocol saveDataSource(@RequestParam(value = "_appId", required = true) String _appId,
                                   @RequestBody DataSourceContract dataSource) {
        String account = AuthTool.currentUser().getAccount();
        boolean result = dataAdminService.saveDataSource(account, dataSource);
        if (result) {
            return new Protocol(0, "保存成功");
        }
        return new Protocol(101, "保存失败");
    }

    @RequestMapping(value = "/removeDataSource", method = RequestMethod.POST)
    public Protocol<Boolean> removeDataSource(@RequestParam(value = "_appId", required = true) String _appId,
                                              @RequestParam(value = "id", required = true) Long id) {
        boolean success = dataAdminService.removeDataSource(id);
        return new Protocol<>(success);
    }

    @RequestMapping(value = "/findDataSource", method = RequestMethod.GET)
    public Protocol<PagerContract<DataSourceContract>> findDataSource(@RequestParam(value = "_appId", required = true) String _appId,
                                                                      @RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                                      @RequestParam(value = "pageSize", required = true) int pageSize,
                                                                      @RequestParam(value = "datasourceType", required = false) String datasourceType) {
        PagerContract<DataSourceContract> pagerContract = dataAdminService.findDatasource(pageIndex, pageSize, datasourceType);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findDataSourceByType", method = RequestMethod.GET)
    public Protocol<List<DataSource>> findDataSourceByType(@RequestParam(value = "_appId", required = true) String _appId,
                                                           @RequestParam(value = "datasourceType", required = true) String datasourceType) {
        List<DataSource> dataSources = this.dataAdminService.findDataSourceByType(datasourceType);
        return new Protocol<>(dataSources);
    }

    @RequestMapping(value = "/saveDataName", method = RequestMethod.POST)
    public Protocol saveDataName(String _appId, @RequestBody DataNameContract dataNameContract) {
        String account = AuthTool.currentUser().getAccount();
        boolean result = dataAdminService.saveDataName(account, dataNameContract);
        if (result) {
            return new Protocol(0, "保存成功");
        }
        return new Protocol(101, "保存失败");
    }

    @RequestMapping(value = "/removeDataName", method = RequestMethod.POST)
    public Protocol<Boolean> removeDataName(@RequestParam(value = "_appId", required = true) String _appId,
                                            @RequestParam(value = "id", required = true) Long id) {
        boolean success = dataAdminService.removeDataName(id);
        return new Protocol<>(success);
    }

    @RequestMapping(value = "/findDataName", method = RequestMethod.GET)
    public Protocol<PagerContract<DataNameContract>> findDataName(@RequestParam(value = "_appId", required = true) String _appId,
                                                                  @RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                                  @RequestParam(value = "pageSize", required = true) int pageSize,
                                                                  @RequestParam(value = "datasourceType", required = false) String datasourceType,
                                                                  @RequestParam(value = "datasourceId", required = false) Long datasourceId,
                                                                  @RequestParam(value = "nameHint", required = false) String nameHint) {
        PagerContract<DataNameContract> pagerContract = dataAdminService.findDataName(pageIndex, pageSize, datasourceType, datasourceId);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findDataNameByType", method = RequestMethod.GET)
    public Protocol<List<DataNameContract>> findDataNameByType(@RequestParam(value = "_appId", required = true) String _appId,
                                                               @RequestParam(value = "datasourceType", required = true) String datasourceType) {
        return new Protocol<>(dataAdminService.findDataNameByType(datasourceType));
    }

    @RequestMapping(value = "/dataOptions", method = RequestMethod.GET)
    public Protocol<List<DataOption>> dataOptions(@RequestParam(value = "_appId", required = true) String _appId) {
        return new Protocol<>(this.dataAdminService.dataOptions());
    }
}
