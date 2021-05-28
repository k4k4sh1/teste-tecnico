package br.com.totvs.testetecnico.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailChecker, String> {
    @Override
    public void initialize(EmailChecker constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains(".") && value.contains("@");
    }
}
