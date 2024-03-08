package com.example.securitynew.validator;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.exception.PasswordValidatorException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,
        UserRegistrationRequestDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRegistrationRequestDto userRegistrationRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        password = userRegistrationRequestDto.getPassword();
        repeatPassword = userRegistrationRequestDto.getRepeatPassword();
        if (!password.equals(repeatPassword)) {
            throw new PasswordValidatorException("your password is not correct");
        }
        return true;
    }
}
