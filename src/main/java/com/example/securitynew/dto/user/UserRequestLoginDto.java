package com.example.securitynew.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestLoginDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
