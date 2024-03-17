package com.example.securitynew.service.impl;

import com.example.securitynew.model.CartItem;
import com.example.securitynew.repository.CartItemRepository;
import com.example.securitynew.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem cartItemFindById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(()
                -> new UsernameNotFoundException("Can't find cartItem"));
    }
}
