package com.example.securitynew.dto.cartitem;

import lombok.Data;

@Data
public class CartItemRequestDto {
    private Long cartItemId;
    private Long bookId;
    private int quantity;
}
