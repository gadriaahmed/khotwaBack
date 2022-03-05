package com.back.khotwa.util.validation.validator;

import com.back.khotwa.util.validation.annotation.PasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;


public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String password, ConstraintValidatorContext constraintValidatorContext) {
        final org.passay.PasswordValidator validator = new org.passay.PasswordValidator(Arrays.asList(new LengthRule(8,30),new DigitCharacterRule(1),new UppercaseCharacterRule(1),new LowercaseCharacterRule(1),new SpecialCharacterRule(1),new WhitespaceRule()));
        final RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
