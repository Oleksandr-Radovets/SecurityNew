package com.example.securitynew.service.impl;

import com.example.securitynew.dto.shoppingCart.ShoppingCartResponseDto;
import com.example.securitynew.mapper.BookMapper;
import com.example.securitynew.mapper.ShoppingCartMapper;
import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.ShoppingCart;
import com.example.securitynew.model.User;
import com.example.securitynew.repository.BookRepository;
import com.example.securitynew.repository.CartItemRepository;
import com.example.securitynew.repository.ShoppingCartRepository;
import com.example.securitynew.repository.UserRepository;
import com.example.securitynew.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartMapper shoppingCartMapper;

    public void addBookShoppingCart(Long idBook, int quantity) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(name).get();
        ShoppingCart byUserId = shoppingCartRepository.findByUserId(user.getId());
        CartItem cartItem;
        if (byUserId != null) {
            cartItem = new CartItem();
            cartItem.setBook(bookRepository.findById(idBook).get());
            cartItem.setQuantity(quantity);
            CartItem saveCartItem = cartItemRepository.save(cartItem);
            byUserId.setCartItemSet(Set.of(saveCartItem));
        } else {
            cartItem = new CartItem();
            cartItem.setBook(bookRepository.findById(idBook).get());
            cartItem.setQuantity(quantity);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCart.getCartItemSet()
                    .add(cartItemRepository
                            .save(cartItem));
            shoppingCartRepository.save(shoppingCart);
        }
    }

    public ShoppingCartResponseDto getShoppingCartUser(Long id) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findByUserId(id));
    }

    public ShoppingCartResponseDto update(Long idCartItem, int quantity) {
        String name = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(name).get();
        ShoppingCart byUserId = shoppingCartRepository.findByUserId(user.getId());
        CartItem cartItem = cartItemRepository.findById(idCartItem).get();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(byUserId);
    }

    public void deleteCartItemInShoppingCart(Long id) {
        cartItemRepository.deleteById(id);
    }
}
