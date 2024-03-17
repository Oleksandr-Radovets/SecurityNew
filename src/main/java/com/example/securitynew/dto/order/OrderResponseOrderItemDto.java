package com.example.securitynew.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseOrderItemDto {
    private Long id;
    private Long bookId;
    private int quantity;
}
