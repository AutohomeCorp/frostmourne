package com.autohome.frostmourne.monitor.service.core.template;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertTemplate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertTemplateRepository;
import com.autohome.frostmourne.monitor.model.contract.*;
import com.autohome.frostmourne.monitor.model.enums.TemplateType;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.autohome.frostmourne.monitor.transform.AlertTemplateTransformer;
import com.github.pagehelper.PageInfo;

@Service
public class AlertTemplateService implements IAlertTemplateService {

    @Resource
    private IAlertTemplateRepository alertTemplateRepository;

    @Lazy
    @Resource
    private IDataAdminService dataAdminService;

    @Override
    public void save(AlertTemplateSaveForm form, String account) {
        this.checkSaveParam(form, account);
        AlertTemplate record = AlertTemplateTransformer.saveForm2Model(form);
        record.setModifier(account);
        if (record.getId() == null || record.getId() == 0L) {
            // 新增
            record.setCreator(account);
            alertTemplateRepository.insertSelective(record);
        } else {
            alertTemplateRepository.updateByPrimaryKeySelective(record);
        }
    }

    private void checkSaveParam(AlertTemplateSaveForm form, String account) {
        // TODO 根据验证框架修改
        if (Objects.nonNull(form.getId()) && form.getId() < 0L) {
            throw new ProtocolException(-1, "id无效");
        }
        if (StringUtils.isEmpty(form.getTemplateName())) {
            throw new ProtocolException(-1, "模板名称不能为空");
        } else if (form.getTemplateName().length() > 50) {
            throw new ProtocolException(-1, "模板名称长度不能超过50");
        }

        if (Objects.isNull(form.getTemplateType())) {
            throw new ProtocolException(-1, "模板类型不能为空");
        } else if (form.getTemplateType() != TemplateType.COMMON) {
            // 非通用，必须关联
            if (StringUtils.isEmpty(form.getTemplateUnionCode())) {
                throw new ProtocolException(-1, "关联码不能为空");
            } else if (form.getContent().length() > 200) {
                throw new ProtocolException(-1, "关联码长度不能超过200");
            }
        }

        if (StringUtils.isEmpty(form.getContent())) {
            throw new ProtocolException(-1, "模板内容不能为空");
        } else if (form.getContent().length() > 5000) {
            throw new ProtocolException(-1, "模板内容长度不能超过5000");
        }

    }

    @Override
    public Optional<AlertTemplateContract> getContract(Long id) {
        return alertTemplateRepository.getById(id).map(record -> {
            AlertTemplateContract contract = AlertTemplateTransformer.model2Contract(record);
            this.fillExtend2Contracts(Collections.singletonList(contract));
            return contract;
        });
    }

    @Override
    public PagerContract<AlertTemplateContract> findContract(AlertTemplateQueryForm form) {
        PageInfo<AlertTemplate> recordPage = alertTemplateRepository.find(form);
        List<AlertTemplateContract> contracts = AlertTemplateTransformer.model2Contract(recordPage.getList());
        this.fillExtend2Contracts(contracts);
        return new PagerContract<>(contracts, recordPage.getPageSize(), recordPage.getPageNum(), (int)recordPage.getTotal());
    }

    private void fillExtend2Contracts(List<AlertTemplateContract> contracts) {
        if (CollectionUtils.isEmpty(contracts)) {
            return;
        }
        this.fillTemplateTypeTreeExtend2Contracts(contracts);
    }

    private void fillTemplateTypeTreeExtend2Contracts(List<AlertTemplateContract> contracts) {
        this.fillTemplateTypeTreeCommonExtend2Contracts(contracts);
        this.fillTemplateTypeTreeDataNameExtend2Contracts(contracts);
    }

    private void fillTemplateTypeTreeCommonExtend2Contracts(List<AlertTemplateContract> contracts) {
        contracts.stream().filter(item -> TemplateType.COMMON.equals(item.getTemplateType())).forEach(item -> {
            item.setTemplateTypeTreeValues(Collections.singletonList(TemplateType.COMMON.name()));
            item.setTemplateTypeTreeLabels(Collections.singletonList(TemplateType.COMMON.getDisplayName()));
        });
    }

    private void fillTemplateTypeTreeDataNameExtend2Contracts(List<AlertTemplateContract> contracts) {
        // 关联数据名
        List<AlertTemplateContract> dataNameContracts =
            contracts.stream().filter(item -> TemplateType.DATA_NAME.equals(item.getTemplateType())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dataNameContracts)) {
            return;
        }
        List<String> unionCodes = dataNameContracts.stream().map(AlertTemplateContract::getTemplateUnionCode).distinct().collect(Collectors.toList());
        Map<String, DataNameContract> dataNameMap = dataAdminService.mapDataNameByNames(unionCodes);
        // 获取数据源名称
        List<Long> dataSourceIds = dataNameMap.values().stream().map(DataNameContract::getDataSourceId).collect(Collectors.toList());
        Map<Long, DataSource> dataSourceMap = dataAdminService.mapDataSourceByIds(dataSourceIds);

        dataNameContracts.forEach(item -> {
            if (DataSourceType.http.name().equalsIgnoreCase(item.getTemplateUnionCode())) {
                // http
                item.setTemplateTypeTreeValues(Arrays.asList(TemplateType.DATA_NAME.name(), DataSourceType.http.name()));
                item.setTemplateTypeTreeLabels(Arrays.asList(TemplateType.DATA_NAME.getDisplayName(), DataSourceType.http.name()));
            } else {
                DataNameContract contract = dataNameMap.get(item.getTemplateUnionCode());
                if (contract == null) {
                    item.setTemplateTypeTreeValues(Arrays.asList(TemplateType.DATA_NAME.name(), item.getTemplateUnionCode()));
                    item.setTemplateTypeTreeLabels(Arrays.asList(TemplateType.DATA_NAME.getDisplayName(), item.getTemplateUnionCode()));
                } else {
                    item.setTemplateTypeTreeValues(Arrays.asList(TemplateType.DATA_NAME.name(), contract.getDatasourceType().name(),
                        String.valueOf(contract.getDataSourceId()), contract.getDataName()));
                    item.setTemplateTypeTreeLabels(Arrays.asList(TemplateType.DATA_NAME.getDisplayName(), contract.getDatasourceType().name(),
                        Optional.ofNullable(dataSourceMap.get(contract.getDataSourceId())).map(DataSource::getDatasourceName)
                            .orElse(String.valueOf(contract.getDataSourceId())),
                        contract.getDisplayName()));
                }
            }
        });
    }

    @Override
    public List<TreeDataOption> listTemplateTypeOptions() {
        return Arrays.stream(TemplateType.values()).map(this::parseTemplateTypeOption).collect(Collectors.toList());
    }

    private TreeDataOption parseTemplateTypeOption(TemplateType templateType) {
        TreeDataOption option = new TreeDataOption(templateType.name(), templateType.getDisplayName());
        if (templateType == TemplateType.DATA_NAME) {
            option.setChildren(this.listTemplateTypeOptionDataName());
        }
        return option;
    }

    private List<TreeDataOption> listTemplateTypeOptionDataName() {
        return dataAdminService.listDataOptions();
    }

}
