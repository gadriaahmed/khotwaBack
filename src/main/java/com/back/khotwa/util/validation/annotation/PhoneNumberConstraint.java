package com.back.khotwa.util.validation.annotation;

import com.back.khotwa.util.validation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {

    String message() default "Invalid phone number format ";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
