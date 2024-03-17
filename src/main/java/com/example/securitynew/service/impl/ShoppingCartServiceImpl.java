package com.example.securitynew.service.impl;

import com.example.securitynew.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.securitynew.mapper.BookMapper;
import com.example.securitynew.mapper.ShoppingCartMapper;
import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.ShoppingCart;
import com.example.securitynew.model.User;
import com.example.securitynew.repository.ShoppingCartRepository;
import com.example.securitynew.repository.UserRepository;
import com.example.securitynew.service.BookService;
import com.example.securitynew.service.CartItemService;
import com.example.securitynew.service.ShoppingCartService;
import com.example.securitynew.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private BookService bookService;
    private BookMapper bookMapper;
    private CartItemService cartItemService;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartMapper shoppingCartMapper;
    private UserService userService;

    @Transactional
    public void addBookShoppingCart(Long idBook, int quantity) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Can't find user"));
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseGet(() -> createShoppingCart(user));
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookMapper.toModel(bookService.findById(idBook)));
        cartItem.setQuantity(quantity);
        shoppingCart.getCartItems().add(cartItemService.saveCartItem(cartItem));
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
        User user = userService.findUserByEmail(name)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Can't find user"));
        ShoppingCart byUserId = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(()
                        -> new UsernameNotFoundException("Can't find ShoppingCart"));
        CartItem cartItem = cartItemService.cartItemFindById(idCartItem);
        cartItem.setQuantity(quantity);
        cartItemService.saveCartItem(cartItem);
        return shoppingCartMapper.toDto(byUserId);
    }

    public void deleteCartItemInShoppingCart(Long id) {
        cartItemService.deleteCartItem(id);
    }
}
