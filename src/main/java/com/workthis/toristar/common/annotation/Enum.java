package com.workthis.toristar.common.annotation;

import com.workthis.toristar.common.validator.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {EnumValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {
    String message() default "Invalid Enum Value.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
