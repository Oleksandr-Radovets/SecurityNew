package com.example.securitynew.dto.user;

import lombok.Data;
import lombok.ToString;

@Data
public class UserResponseDto {

    @ToString.Exclude
    private Long id;
    @ToString.Exclude
    private String email;

}
