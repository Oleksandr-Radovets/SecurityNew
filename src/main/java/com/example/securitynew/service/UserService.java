package com.example.securitynew.service;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserRequestLoginDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;

    boolean authentication(UserRequestLoginDto userRequestLoginDto);
}
