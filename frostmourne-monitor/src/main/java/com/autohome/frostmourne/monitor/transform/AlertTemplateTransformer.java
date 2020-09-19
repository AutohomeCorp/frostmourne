package com.autohome.frostmourne.monitor.transform;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.autohome.frostmourne.monitor.contract.AlertTemplateContract;
import com.autohome.frostmourne.monitor.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.contract.enums.AlertTemplateEnums.TemplateType;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertTemplate;
import org.springframework.util.CollectionUtils;

public class AlertTemplateTransformer {

    public static AlertTemplateContract model2Contract(AlertTemplate alertTemplate) {
        if (alertTemplate == null) {
            return null;
        }
        AlertTemplateContract alertTemplateContract = new AlertTemplateContract();
        alertTemplateContract.setId(alertTemplate.getId());
        alertTemplateContract.setTemplateName(alertTemplate.getTemplateName());
        alertTemplateContract.setTemplateType(alertTemplate.getTemplateType());
        alertTemplateContract.setTemplateUnionCode(alertTemplate.getTemplateUnionCode());
        alertTemplateContract.setContent(alertTemplate.getContent());
        alertTemplateContract.setCreator(alertTemplate.getCreator());
        alertTemplateContract.setCreateAt(alertTemplate.getCreateAt());
        alertTemplateContract.setModifier(alertTemplate.getModifier());
        alertTemplateContract.setModifyAt(alertTemplate.getModifyAt());
        return alertTemplateContract;
    }

    public static List<AlertTemplateContract> model2Contract(List<AlertTemplate> alertTemplates) {
        if (CollectionUtils.isEmpty(alertTemplates)) {
            return Collections.emptyList();
        }
        return alertTemplates.stream()
                .map(AlertTemplateTransformer::model2Contract)
                .collect(Collectors.toList());
    }

    public static AlertTemplate saveForm2Model(AlertTemplateSaveForm alertTemplateSaveForm) {
        if (alertTemplateSaveForm == null) {
            return null;
        }
        AlertTemplate alertTemplate = new AlertTemplate();
        alertTemplate.setId(alertTemplateSaveForm.getId());
        alertTemplate.setTemplateName(alertTemplateSaveForm.getTemplateName());
        alertTemplate.setTemplateType(Optional.ofNullable(alertTemplateSaveForm.getTemplateType()).map(TemplateType::name).orElse(null));
        alertTemplate.setTemplateUnionCode(alertTemplateSaveForm.getTemplateUnionCode());
        alertTemplate.setContent(alertTemplateSaveForm.getContent());
        return alertTemplate;
    }

}
