package com.example.securitynew.dto.shoppingCart;

import com.example.securitynew.model.CartItem;
import java.util.Set;
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private Set<CartItem> cartItemSet;
}
