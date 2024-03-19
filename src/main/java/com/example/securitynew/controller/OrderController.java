package com.example.securitynew.controller;

import com.example.securitynew.dto.order.OrderDto;
import com.example.securitynew.dto.order.OrderItemResponseDto;
import com.example.securitynew.dto.order.OrderRequestDto;
import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.StatusResponseDto;
import com.example.securitynew.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/orders")
    public OrderResponseDto saveOrder(
            @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto.getShippingAddress());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders() {
        return orderService.allOrdersUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/update/{orderId}")
    public StatusResponseDto updateOrderItemStatus(@PathVariable Long orderId,
                                                   @RequestBody
                                                   OrderDto orderDto) {
        return orderService.update(orderId, orderDto.getStatus());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders/{orderId}/items")
    public List<OrderItemResponseDto> getAllOrderItems(@PathVariable Long orderId) {
        return orderService.getAllOrderItemByOrder(orderId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders/{orderId}/items/{itemId}")
    public OrderItemResponseDto getOrderItem(@PathVariable Long orderId,
                                             @PathVariable Long itemId) {
        return orderService.getOrderItem(orderId, itemId);
    }
}
