package com.example.securitynew.service.impl;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.mapper.UserMapper;
import com.example.securitynew.repository.UserRepository;
import com.example.securitynew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }
        return userMapper
                .toUserResponse(userRepository
                        .save(userMapper
                                .toModel(userRegistrationRequestDto)));
    }
}
