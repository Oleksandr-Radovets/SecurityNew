package com.example.securitynew.service.impl;

import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.OrderResponseOrderItemDto;
import com.example.securitynew.dto.order.OrderResponseStatusDto;
import com.example.securitynew.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.securitynew.mapper.OrderMapper;
import com.example.securitynew.model.Book;
import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.Order;
import com.example.securitynew.model.Status;
import com.example.securitynew.model.User;
import com.example.securitynew.repository.OrderRepository;
import com.example.securitynew.service.OrderItemService;
import com.example.securitynew.service.OrderService;
import com.example.securitynew.service.ShoppingCartService;
import com.example.securitynew.service.UserService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private OrderRepository orderRepository;
    private OrderItemService orderItemService;
    private OrderMapper orderMapper;

    public OrderResponseDto createOrder(String shippingAddress) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(name).orElseThrow(()
                -> new UsernameNotFoundException("Can't find user"));
        ShoppingCartResponseDto shoppingCartUser = shoppingCartService
                .getShoppingCartUser(user.getId());
        Order order = new Order();
        order.setUser(user);
        order.setTotal(shoppingCartUser.getCartItemSet().stream()
                .map(CartItem::getBook)
                .map(Book::getPrice).reduce((a, b) -> a = a.add(b)).orElseThrow());
        order.setOrderData(LocalDateTime.now());
        order.setShippingAddress(shippingAddress);
        order.setStatus(Status.PENDING);
        order.getOrderItemSet()
                .addAll(orderItemService
                        .saveAll(new HashSet<>(shoppingCartUser
                                .getCartItemSet())));
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderResponseDto> allOrdersUser() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderResponseStatusDto update(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new UsernameNotFoundException("Can't find user"));
        order.setStatus(Arrays
                .stream(Status.values())
                .filter(s -> s.equals(status)).toList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("this status is not correct")));
        orderRepository.save(order);
        return orderMapper.toDtoStatus(order);
    }

    public List<OrderResponseOrderItemDto> getAllOrderItemByOrder(Long orderId) {
        List<OrderResponseOrderItemDto> list = orderRepository
                .findById(orderId)
                .get()
                .getOrderItemSet()
                .stream()
                .map(orderMapper::toDtoOrderItem)
                .toList();
        return list;
    }

    public OrderResponseOrderItemDto getOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Can't orderItem by order id "
                        + orderId));
        return order.getOrderItemSet()
                .stream()
                .filter(orderItem -> orderItem
                        .getId()
                        .equals(orderItemId))
                .map(orderMapper::toDtoOrderItem)
                .findFirst()
                .orElseThrow(()
                        -> new RuntimeException("Can't orderItem by orderItemId "
                        + orderItemId));
    }
}
