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
        alertTemplateContract.setTemplateName(alertTemplate.getTemplate_name());
        alertTemplateContract.setTemplateType(alertTemplate.getTemplate_type());
        alertTemplateContract.setTemplateUnionCode(alertTemplate.getTemplate_union_code());
        alertTemplateContract.setContent(alertTemplate.getContent());
        alertTemplateContract.setCreator(alertTemplate.getCreator());
        alertTemplateContract.setCreateAt(alertTemplate.getCreate_at());
        alertTemplateContract.setModifier(alertTemplate.getModifier());
        alertTemplateContract.setModifyAt(alertTemplate.getModify_at());
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
        alertTemplate.setTemplate_name(alertTemplateSaveForm.getTemplateName());
        alertTemplate.setTemplate_type(Optional.ofNullable(alertTemplateSaveForm.getTemplateType()).map(TemplateType::name).orElse(null));
        alertTemplate.setTemplate_union_code(alertTemplateSaveForm.getTemplateUnionCode());
        alertTemplate.setContent(alertTemplateSaveForm.getContent());
        return alertTemplate;
    }

}
