package com.example.securitynew.service;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserRequestLoginDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.model.User;
import java.util.Optional;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;

    boolean authentication(UserRequestLoginDto userRequestLoginDto);

    Optional<User> findUserByEmail(String email);
}
