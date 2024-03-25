package com.example.securitynew.service;

import com.example.securitynew.dto.order.OrderItemResponseDto;
import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.StatusResponseDto;
import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(String shippingAddress);

    List<OrderResponseDto> allOrdersUser();

    StatusResponseDto update(Long orderId, String status);

    List<OrderItemResponseDto> getAllOrderItemByOrder(Long orderId);

    public OrderItemResponseDto getOrderItem(Long orderId, Long orderItemId);
}
