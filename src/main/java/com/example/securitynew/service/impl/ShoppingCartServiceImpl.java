package com.example.securitynew.service.impl;

import com.example.securitynew.dto.shoppingcart.ShoppingCartResponseDto;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartMapper shoppingCartMapper;

    @Transactional
    public void addBookShoppingCart(Long idBook, int quantity) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).get();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseGet(() -> createShoppingCart(user));
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findById(idBook).get());
        cartItem.setQuantity(quantity);
        shoppingCart.getCartItems().add(cartItemRepository.save(cartItem));
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCart;
    }

    public ShoppingCartResponseDto getShoppingCartUser(Long id) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findByUserId(id).get());
    }

    public ShoppingCartResponseDto update(Long idCartItem, int quantity) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(name).get();
        ShoppingCart byUserId = shoppingCartRepository.findByUserId(user.getId()).get();
        CartItem cartItem = cartItemRepository.findById(idCartItem).get();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(byUserId);
    }

    public void deleteCartItemInShoppingCart(Long id) {
        cartItemRepository.deleteById(id);
    }
}
