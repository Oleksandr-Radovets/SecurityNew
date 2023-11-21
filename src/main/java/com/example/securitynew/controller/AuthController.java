package com.example.securitynew.controller;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public UserResponseDto registration(@RequestBody
                                        @Valid UserRegistrationRequestDto
                                                    userRegistrationRequestDto)
            throws RegistrationException {
        return userService.registerUser(userRegistrationRequestDto);
    }
}
