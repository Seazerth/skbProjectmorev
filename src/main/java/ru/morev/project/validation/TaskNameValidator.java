package ru.morev.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TaskNameValidator implements ConstraintValidator<ValidTaskName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^[А-Яа-яA-Za-z0-9\\s]+$");
    }
}
