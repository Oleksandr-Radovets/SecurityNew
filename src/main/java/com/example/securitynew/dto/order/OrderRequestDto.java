package com.example.securitynew.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long id;
    private String shippingAddress;
    private String status;
}
