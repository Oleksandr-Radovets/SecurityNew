package com.example.securitynew.controller;

import com.example.securitynew.dto.user.UserLoginRequestDto;
import com.example.securitynew.dto.user.UserLoginResponseDto;
import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.security.AuthenticationService;
import com.example.securitynew.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public UserLoginResponseDto login(@RequestBody
                                      @Valid UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.authentication(userLoginRequestDto);
    }

    @PostMapping("/auth/register")
    public UserResponseDto registration(@RequestBody
                                        @Valid UserRegistrationRequestDto
                                                    userRegistrationRequestDto)
            throws RegistrationException {
        return userService.register(userRegistrationRequestDto);
    }

}
