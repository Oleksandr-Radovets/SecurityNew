package com.example.securitynew.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {
    String message() default "Fields values don't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String repeatPassword();
}
