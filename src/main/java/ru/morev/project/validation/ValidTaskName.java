package ru.morev.project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TaskNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaskName {
    String message() default "Имя задачи недопустимо";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
