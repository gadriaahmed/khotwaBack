package com.back.khotwa.util.validation.annotation;

import com.back.khotwa.util.validation.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {

    String message() default "Invalid email format";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};

}
