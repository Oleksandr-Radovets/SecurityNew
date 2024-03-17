package com.example.securitynew.controller;

import com.example.securitynew.dto.order.OrderRequestDto;
import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.OrderResponseOrderItemDto;
import com.example.securitynew.dto.order.OrderResponseStatusDto;
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

@RequestMapping("/api/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/order")
    public OrderResponseDto saveOrder(
            @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto.getShippingAddress());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public List<OrderResponseDto> allOrders() {
        return orderService.allOrdersUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/update/{orderId}")
    public OrderResponseStatusDto updateStatusOrderItem(@PathVariable Long orderId,
                                                        @RequestBody
                                                        OrderRequestDto orderRequestDto) {
        return orderService.update(orderId, orderRequestDto.getStatus());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/allOrderItem/{orderId}")
    public List<OrderResponseOrderItemDto> getAllOrderItem(@PathVariable Long orderId) {
        return orderService.getAllOrderItemByOrder(orderId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/orders/{orderId}/items/{itemId}")
    public OrderResponseOrderItemDto getOrderItemByIdOrder(@PathVariable Long orderId,
                                                           @PathVariable Long orderItemId) {
        return orderService.getOrderItem(orderId, orderItemId);
    }
}
