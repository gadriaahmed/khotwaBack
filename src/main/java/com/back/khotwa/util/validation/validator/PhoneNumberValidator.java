package com.back.khotwa.util.validation.validator;

import com.back.khotwa.util.validation.annotation.PhoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumberField, ConstraintValidatorContext constraintValidatorContext) {

        return phoneNumberField!=null && phoneNumberField.matches("[0-9]+") && (phoneNumberField.length()>8) && (phoneNumberField.length()<14);
    }
}
