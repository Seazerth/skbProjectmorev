package ru.morev.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.morev.project.model.TodoList;

public class CombinedValidator implements ConstraintValidator<CombinedValidation, TodoList> {

    @Override
    public boolean isValid(TodoList list, ConstraintValidatorContext context) {
        return list.getName() != null && list.getEvents() != null && !list.getEvents().isEmpty();
    }
}
