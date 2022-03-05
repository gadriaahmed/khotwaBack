package com.back.khotwa.util.validation.annotation;

import com.back.khotwa.util.validation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "Invalid password format";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
