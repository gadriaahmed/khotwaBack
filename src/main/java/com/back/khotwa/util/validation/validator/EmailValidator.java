package com.back.khotwa.util.validation.validator;

import com.back.khotwa.util.validation.annotation.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailConstraint,String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext constraintValidatorContext) {
        return validateEmail(emailField);
    }
    private boolean validateEmail(final String email){
        pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
                return matcher.matches();
    }
}
