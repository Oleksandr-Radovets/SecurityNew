package com.example.securitynew.service.impl;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserRequestLoginDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.mapper.UserMapper;
import com.example.securitynew.model.User;
import com.example.securitynew.repository.UserRepository;
import com.example.securitynew.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("user with this email already exist");
        }
        User user = userMapper.toModel(userRegistrationRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public boolean authentication(UserRequestLoginDto requestLoginDto) {
        User user = userRepository.findByEmail(requestLoginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("your password is incorrect"));
        return passwordEncoder.matches(requestLoginDto.getPassword(),
                user.getPassword());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
