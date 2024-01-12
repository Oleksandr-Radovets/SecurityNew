package com.example.securitynew.controller;

import com.example.securitynew.dto.user.UserLoginRequestDto;
import com.example.securitynew.dto.user.UserLoginResponseDto;
import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.security.AuthenticationService;
import com.example.securitynew.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user management", description = "Endpoints for managing users")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "create new user", description = "create new user")
    @PostMapping("/auth/register")
    public UserResponseDto registration(@RequestBody
                                        @Valid UserRegistrationRequestDto
                                                    userRegistrationRequestDto)
            throws RegistrationException {
        return userService.registerUser(userRegistrationRequestDto);
    }

    @Operation(summary = "write login user", description = "write login user")
    @PostMapping("/auth/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authentication(requestDto);
    }
}
