package com.example.securitynew.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotEmpty
        @Size(min = 5, max = 50)
        @Email
        String email,
        @NotBlank
        @Size(min = 6, max = 100)
        String password
) {
}
