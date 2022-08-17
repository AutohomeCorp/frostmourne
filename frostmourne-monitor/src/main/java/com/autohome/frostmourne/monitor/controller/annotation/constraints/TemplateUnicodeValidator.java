package com.autohome.frostmourne.monitor.controller.annotation.constraints;

import com.autohome.frostmourne.monitor.model.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.model.enums.TemplateType;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * template unicode validator annotation
 *
 * @author limbo
 * @since 2022/8/17 11:02
 */
public class TemplateUnicodeValidator implements ConstraintValidator<TemplateUnicodeAnnotation, AlertTemplateSaveForm> {

    @Override
    public boolean isValid(AlertTemplateSaveForm form, ConstraintValidatorContext context) {
        if (form.getTemplateType() != TemplateType.COMMON) {
            // 非通用，必须关联
            if (StringUtils.isEmpty(form.getTemplateUnionCode())) {
                return false;
            }
            if (form.getTemplateUnionCode().length() > 200) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(TemplateUnicodeAnnotation constraintAnnotation) {
    }
}
