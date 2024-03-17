package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.model.CartItem;
import com.example.securitynew.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItem orderItemMapperList(CartItem cartItem);
}
