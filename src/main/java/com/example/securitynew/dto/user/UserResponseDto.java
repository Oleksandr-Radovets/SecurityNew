package com.example.securitynew.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
public class UserResponseDto {

    @ToString.Exclude
    private Long id;
    @ToString.Exclude
    private String email;

}
