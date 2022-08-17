package com.autohome.frostmourne.monitor.controller.annotation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * template unicode validator annotation
 *
 * @author limbo
 * @since 2022/8/17 11:00
 */
@Constraint(validatedBy = TemplateUnicodeValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TemplateUnicodeAnnotation {
    String message() default "{TemplateUnicodeAnnotation.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
