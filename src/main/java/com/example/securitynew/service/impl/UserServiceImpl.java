package com.example.securitynew.service.impl;

import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.exception.RegistrationException;
import com.example.securitynew.mapper.UserMapper;
import com.example.securitynew.model.Role;
import com.example.securitynew.model.Role.RoleName;
import com.example.securitynew.model.User;
import com.example.securitynew.repository.UserRepository;
import com.example.securitynew.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bcryptPasswordEncoder;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }
        User user = new User();
        user.setPassword(bcryptPasswordEncoder.encode(userRegistrationRequestDto.getPassword()));
        user.setEmail(userRegistrationRequestDto.getEmail());
        Role role = new Role();
        role.setRoleName(RoleName.USER);
        user.setRoles(Set.of(role));
        User save = userRepository.save(user);
        return userMapper.toUserResponse(save);
    }
}
