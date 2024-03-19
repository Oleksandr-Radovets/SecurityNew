package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.dto.order.OrderItemResponseDto;
import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.StatusResponseDto;
import com.example.securitynew.model.Order;
import com.example.securitynew.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderResponseDto toDto(Order order);

    StatusResponseDto toDtoStatus(Order order);

    OrderItemResponseDto toDtoOrderItem(OrderItem orderitem);
}
