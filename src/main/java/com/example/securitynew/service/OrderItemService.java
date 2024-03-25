package com.example.securitynew.service;

import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.OrderItem;
import java.util.List;
import java.util.Set;

public interface OrderItemService {
    List<OrderItem> saveAll(Set<CartItem> list);

}
