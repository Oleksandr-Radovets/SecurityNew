package com.example.securitynew.service.impl;

import com.example.securitynew.mapper.OrderItemMapper;
import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.OrderItem;
import com.example.securitynew.repository.OrderItemRepository;
import com.example.securitynew.service.OrderItemService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> saveAll(Set<CartItem> list) {
        return orderItemRepository.saveAll(list.stream()
                .map(orderItemMapper::toOrderItem)
                .collect(Collectors.toSet()));
    }
}
