package com.example.securitynew.service;

import com.example.securitynew.model.CartItem;

public interface CartItemService {

    void deleteCartItem(Long id);

    CartItem saveCartItem(CartItem cartItem);

    CartItem cartItemFindById(Long id);
}
