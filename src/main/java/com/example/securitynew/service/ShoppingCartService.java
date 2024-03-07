package com.example.securitynew.service;

import com.example.securitynew.dto.shoppingCart.ShoppingCartResponseDto;
public interface ShoppingCartService {

    void addBookShoppingCart(Long idBook, int quantity);
    ShoppingCartResponseDto getShoppingCartUser(Long id);
    ShoppingCartResponseDto update(Long idCartItem, int quantity);
    void deleteCartItemInShoppingCart(Long id);
}
