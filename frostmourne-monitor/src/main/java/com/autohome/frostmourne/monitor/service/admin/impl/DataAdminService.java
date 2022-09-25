package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.config.properties.EncryptProperties;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.model.vo.DataSourceVO;
import com.autohome.frostmourne.monitor.tool.AESUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.jdbc.IDataSourceJdbcManager;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataName;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataNameRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataSourceRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IMetricRepository;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataOption;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceOption;
import com.autohome.frostmourne.monitor.model.contract.TreeDataOption;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.autohome.frostmourne.monitor.transform.DataNameTransformer;
import com.autohome.frostmourne.monitor.transform.DataSourceTransformer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class DataAdminService implements IDataAdminService {

    @Resource
    private IDataSourceRepository dataSourceRepository;

    @Resource
    private IDataNameRepository dataNameRepository;

    @Resource
    private IMetricRepository metricRepository;

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;
    @Resource
    private IDataSourceJdbcManager dataSourceJdbcManager;

    @Override
    public DataSourceContract findDatasourceById(Long id) {
        Optional<DataSource> optionalDataSource = dataSourceRepository.selectByPrimaryKey(id);
        return optionalDataSource.map(DataSourceTransformer::model2Contract).orElse(null);
    }

    @Override
    public boolean saveDataSource(String account, DataSourceContract dataSourceContract) {
        DataSource dataSource = new DataSource();
        dataSource.setDatasourceName(dataSourceContract.getDatasourceName());
        dataSource.setDatasourceType(dataSourceContract.getDatasourceType());
        dataSource.setModifier(account);
        dataSource.setServiceAddress(dataSourceContract.getServiceAddress());
        dataSource.setModifyAt(new Date());

        if (dataSourceContract.getSettings() != null && dataSourceContract.getSettings().size() > 0) {
            // 解密操作
            Map<String, String> settings = handleEncryptSetting(dataSourceContract);
            // 更新settings
            dataSourceContract.setSettings(settings);
            dataSource.setProperties(JacksonUtil.serialize(dataSourceContract.getSettings()));
        }

        if (dataSourceContract.getId() != null && dataSourceContract.getId() > 0) {
            dataSource.setId(dataSourceContract.getId());
            if (DataSourceType.elasticsearch.equals(dataSource.getDatasourceType())) {
                boolean reloadResult = elasticsearchSourceManager.reloadEsRestClientContainer(new ElasticsearchInfo(dataSourceContract));
                if (!reloadResult) {
                    return false;
                }
            } else if (DataSourceType.mysql.equals(dataSource.getDatasourceType()) || DataSourceType.clickhouse.equals(dataSource.getDatasourceType())) {
                boolean reloadResult = dataSourceJdbcManager.putDataSource(dataSourceContract);
                if (!reloadResult) {
                    return false;
                }
            }
            return dataSourceRepository.updateByPrimaryKeySelective(dataSource) > 0;
        }
        dataSource.setCreator(account);
        dataSource.setCreateAt(new Date());
        return dataSourceRepository.insert(dataSource) > 0;
    }

    @Override
    public boolean removeDataSource(Long id) {
        long datasourceCount = metricRepository.datasourceCount(id);
        if (datasourceCount > 0) {
            throw new ProtocolException(600, "数据源正在使用无法删除");
        }
        return this.dataSourceRepository.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public PagerContract<DataSourceContract> findDatasource(int pageIndex, int pageSize, DataSourceType datasourceType) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<DataSource> list = this.dataSourceRepository.find(datasourceType);
        return new PagerContract<>(
                list.stream()
                        .map(i -> DataSourceTransformer.model2Contract(i, true))
                        .collect(Collectors.toList()),
                page.getPageSize(), page.getPageNum(), (int)page.getTotal());
    }

    @Override
    public List<DataSource> findDataSourceByType(DataSourceType datasourceType) {
        return this.dataSourceRepository.find(datasourceType);
    }

    @Override
    public Map<Long, DataSource> mapDataSourceByIds(List<Long> dataSourceIds) {
        if (CollectionUtils.isEmpty(dataSourceIds)) {
            return Collections.emptyMap();
        }
        List<DataSource> items = dataSourceRepository.findByIdList(dataSourceIds);
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyMap();
        }
        return items.stream().collect(Collectors.toMap(DataSource::getId, item -> item, (v1, v2) -> v1));
    }

    @Override
    public List<DataOption> dataOptions() {
        List<DataSource> dataSourceList = this.dataSourceRepository.find(null);
        List<DataName> dataNameList = this.dataNameRepository.find(null, null, null);
        Map<String, List<DataSourceOption>> dataOptionMap = new HashMap<>();
        for (DataSource dataSource : dataSourceList) {
            DataSourceOption dataSourceOption = new DataSourceOption();
            DataSourceVO dataSourceVO = DataSourceVO.builder()
                    .id(dataSource.getId())
                    .datasourceName(dataSource.getDatasourceName())
                    .datasourceType(dataSource.getDatasourceType())
                    .build();
            dataSourceOption.setDataSourceVO(dataSourceVO);
            dataSourceOption.setDataNameContractList(dataNameList.stream().filter(dataName -> dataName.getDataSourceId().equals(dataSource.getId()))
                .map(DataAdminService::toDataNameContract).collect(Collectors.toList()));
            if (dataOptionMap.containsKey(dataSource.getDatasourceType().name())) {
                dataOptionMap.get(dataSource.getDatasourceType().name()).add(dataSourceOption);
            } else {
                List<DataSourceOption> dataSourceOptionList = new ArrayList<>();
                dataSourceOptionList.add(dataSourceOption);
                dataOptionMap.put(dataSource.getDatasourceType().name(), dataSourceOptionList);
            }
        }

        List<DataOption> dataOptionList = new ArrayList<>();
        for (Map.Entry<String, List<DataSourceOption>> entry : dataOptionMap.entrySet()) {
            DataOption dataOption = new DataOption();
            dataOption.setDatasourceType(entry.getKey());
            dataOption.setDataSourceOptionList(entry.getValue());
            dataOptionList.add(dataOption);
        }

        return dataOptionList;
    }

    @Override
    public List<TreeDataOption> listDataOptions() {
        List<TreeDataOption> dataOptions = this.parseTreeDataOptionByDataOptions(this.dataOptions());
        List<TreeDataOption> options = new ArrayList<>(dataOptions);
        // HTTP
        options.add(new TreeDataOption("http", "http"));
        options.add(new TreeDataOption("ping", "ping"));
        options.add(new TreeDataOption("telnet", "telnet"));
        return options;
    }

    private List<TreeDataOption> parseTreeDataOptionByDataOptions(List<DataOption> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(item -> {
            TreeDataOption option = new TreeDataOption(item.getDatasourceType(), item.getDatasourceType());
            option.setChildren(this.parseTreeDataOptionByDataSourceOptions(item.getDataSourceOptionList()));
            return option;
        }).collect(Collectors.toList());
    }

    private List<TreeDataOption> parseTreeDataOptionByDataSourceOptions(List<DataSourceOption> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(item -> {
            TreeDataOption option = new TreeDataOption(String.valueOf(item.getDataSourceVO().getId()), item.getDataSourceVO().getDatasourceName());
            option.setChildren(this.parseTreeDataOptionByDataNameContracts(item.getDataNameContractList()));
            return option;
        }).collect(Collectors.toList());
    }

    private List<TreeDataOption> parseTreeDataOptionByDataNameContracts(List<DataNameContract> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(item -> new TreeDataOption(item.getDataName(), item.getDisplayName())).collect(Collectors.toList());
    }

    @Override
    public boolean saveDataName(String account, DataNameContract dataNameContract) {
        DataName dataName = new DataName();
        Date now = new Date();
        dataName.setDataName(dataNameContract.getDataName());
        dataName.setModifier(account);
        dataName.setModifyAt(now);
        dataName.setDataSourceId(dataNameContract.getDataSourceId());
        dataName.setDatasourceType(dataNameContract.getDatasourceType());
        dataName.setDisplayName(dataNameContract.getDisplayName());
        dataName.setProperties(JacksonUtil.serialize(dataNameContract.getSettings()));
        dataName.setTimestampField(dataNameContract.getTimestampField());
        if (dataNameContract.getId() != null && dataNameContract.getId() > 0) {
            dataName.setId(dataNameContract.getId());
            return this.dataNameRepository.updateByPrimaryKeySelective(dataName) > 0;
        }
        Optional<DataName> oldDataName = this.dataNameRepository.findByName(dataNameContract.getDataName());
        if (oldDataName.isPresent()) {
            throw new ProtocolException(504, "数据名称发生重复");
        }
        dataName.setCreator(account);
        dataName.setCreateAt(now);
        return this.dataNameRepository.insert(dataName) > 0;
    }

    @Override
    public boolean removeDataName(Long datanameId) {
        long datanameCount = this.metricRepository.datanameCount(datanameId);
        if (datanameCount > 0) {
            throw new ProtocolException(600, "数据名正在使用无法删除");
        }
        return this.dataNameRepository.deleteByPrimaryKey(datanameId) > 0;
    }

    @Override
    public PagerContract<DataNameContract> findDataName(int pageIndex, int pageSize, DataSourceType datasourceType, Long datasourceId, String nameHint) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<DataName> list = this.dataNameRepository.find(datasourceType, datasourceId, nameHint);
        return new PagerContract<>(list.stream().map(DataAdminService::toDataNameContract).collect(Collectors.toList()), page.getPageSize(), page.getPageNum(),
            (int)page.getTotal());
    }

    @Override
    public List<DataNameContract> findDataNameByType(DataSourceType datasourceType) {
        List<DataName> list = this.dataNameRepository.find(datasourceType, null, null);
        return list.stream().map(DataAdminService::toDataNameContract).collect(Collectors.toList());
    }

    @Override
    public DataNameContract findDataNameByName(String name) {
        Optional<DataName> optionalDataName = dataNameRepository.findByName(name);
        return optionalDataName.map(DataNameTransformer::model2Contract).orElse(null);
    }

    @Override
    public Map<String, DataNameContract> mapDataNameByNames(List<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyMap();
        }
        return dataNameRepository.findByNames(names).stream().map(DataNameTransformer::model2Contract)
            .collect(Collectors.toMap(DataNameContract::getDataName, item -> item, (v1, v2) -> v1));
    }

    static DataNameContract toDataNameContract(DataName dataName) {
        DataNameContract dataNameContract = new DataNameContract();
        dataNameContract.setId(dataName.getId());
        dataNameContract.setDataSourceId(dataName.getDataSourceId());
        dataNameContract.setDatasourceType(dataName.getDatasourceType());
        dataNameContract.setDataName(dataName.getDataName());
        dataNameContract.setDisplayName(dataName.getDisplayName());
        dataNameContract.setTimestampField(dataName.getTimestampField());
        dataNameContract.setCreator(dataName.getCreator());
        dataNameContract.setCreateAt(dataName.getCreateAt());
        dataNameContract.setModifier(dataName.getModifier());
        dataNameContract.setModifyAt(dataName.getModifyAt());
        dataNameContract.setSettings(JacksonUtil.deSerialize(dataName.getProperties(), new TypeReference<Map<String, String>>() {}));

        return dataNameContract;
    }

    private Map<String, String> handleEncryptSetting(DataSourceContract dataSourceContract) {
        Map<String, String> settings = dataSourceContract.getSettings();
        List<String> sensitiveFields = EncryptProperties.getInstance().getSensitiveFields();
        if (!CollectionUtils.isEmpty(sensitiveFields)) {
            sensitiveFields.forEach(field -> {
                if (settings.containsKey(field)) {
                    // 判断是否是加密数据, 并更新原键值
                    String decrypt = AESUtils.decrypt(settings.get(field));
                    if (decrypt != null) {
                        settings.put(field, decrypt);
                    }
                }
            });
        }
        return settings;

    }
}
