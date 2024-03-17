package com.example.securitynew.service;

import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.OrderResponseOrderItemDto;
import com.example.securitynew.dto.order.OrderResponseStatusDto;
import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(String shippingAddress);

    List<OrderResponseDto> allOrdersUser();

    OrderResponseStatusDto update(Long orderId, String status);

    List<OrderResponseOrderItemDto> getAllOrderItemByOrder(Long orderId);

    public OrderResponseOrderItemDto getOrderItem(Long orderId, Long orderItemId);
}
