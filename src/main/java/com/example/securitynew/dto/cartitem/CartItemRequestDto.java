package com.example.securitynew.dto.cartitem;

import lombok.Data;

@Data
public class CartItemRequestDto {
    private Long idCartItem;
    private Long idBook;
    private int quantity;
}
