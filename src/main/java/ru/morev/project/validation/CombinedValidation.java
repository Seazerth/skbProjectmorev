package ru.morev.project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CombinedValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CombinedValidation {
    String message() default "Неверные данные";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
