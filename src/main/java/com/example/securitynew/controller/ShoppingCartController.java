package com.example.securitynew.controller;

import com.example.securitynew.dto.cartitem.CartItemRequestDto;
import com.example.securitynew.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.securitynew.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/cart/{id}")
    public ShoppingCartResponseDto getShoppingCart(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartUser(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/cart")
    public void createShoppingCart(@RequestBody CartItemRequestDto cartItem) {
        shoppingCartService.addBookShoppingCart(cartItem.getIdBook(),
                cartItem.getQuantity());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart/cart-items")
    public ShoppingCartResponseDto updateShoppingCart(@RequestBody CartItemRequestDto requestDto) {
        return shoppingCartService.update(requestDto.getIdCartItem(), requestDto.getQuantity());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/api/cart/cart-items/{cartItemId}")
    public void deleteCartItem(@PathVariable Long id) {
        shoppingCartService.deleteCartItemInShoppingCart(id);
    }
}
