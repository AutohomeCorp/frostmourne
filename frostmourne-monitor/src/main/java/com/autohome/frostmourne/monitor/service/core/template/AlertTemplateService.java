package com.autohome.frostmourne.monitor.service.core.template;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.contract.AlertTemplateContract;
import com.autohome.frostmourne.monitor.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.contract.enums.AlertTemplateEnums.TemplateType;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertTemplate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertTemplateRepository;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.autohome.frostmourne.monitor.transform.AlertTemplateTransformer;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class AlertTemplateService implements IAlertTemplateService {

    @Resource
    private IAlertTemplateRepository alertTemplateRepository;

    @Lazy
    @Resource
    private IDataAdminService dataAdminService;

    @Override
    public void save(AlertTemplateSaveForm form,
                     String account) {
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

    private void checkSaveParam(AlertTemplateSaveForm form,
                                String account) {
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
        return alertTemplateRepository.getById(id)
                .map(record -> {
                    AlertTemplateContract contract = AlertTemplateTransformer.model2Contract(record);
                    this.fillExtend2Contracts(Collections.singletonList(contract));
                    return contract;
                });
    }

    @Override
    public PagerContract<AlertTemplateContract> findContract(AlertTemplateQueryForm form) {
        PageInfo<AlertTemplate> recordPage = alertTemplateRepository.find(form);
        List<AlertTemplateContract> contracts = AlertTemplateTransformer.model2Contract(recordPage.getList());
        return new PagerContract<>(contracts, recordPage.getPageSize(), recordPage.getPageNum(), (int) recordPage.getTotal());
    }

    private void fillExtend2Contracts(List<AlertTemplateContract> contracts) {
        if (CollectionUtils.isEmpty(contracts)) {
            return;
        }
        this.fillTemplateUnionCodeExtend2Contracts(contracts);
    }

    private void fillTemplateUnionCodeExtend2Contracts(List<AlertTemplateContract> contracts) {
        List<AlertTemplateContract> dataNameContracts = contracts.stream()
                .filter(item -> TemplateType.DATA_NAME.name().equals(item.getTemplateType()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dataNameContracts)) {
            return;
        }
        List<String> unionCodes = dataNameContracts.stream()
                .map(AlertTemplateContract::getTemplateUnionCode)
                .distinct()
                .collect(Collectors.toList());
        //dataAdminService.findDataNameByName()

    }

}
