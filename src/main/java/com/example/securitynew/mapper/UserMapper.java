package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.dto.user.UserRegistrationRequestDto;
import com.example.securitynew.dto.user.UserResponseDto;
import com.example.securitynew.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto toUserResponse(User user);
}
