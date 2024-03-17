package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.dto.order.OrderResponseDto;
import com.example.securitynew.dto.order.OrderResponseOrderItemDto;
import com.example.securitynew.dto.order.OrderResponseStatusDto;
import com.example.securitynew.model.Order;
import com.example.securitynew.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderResponseDto toDto(Order order);

    OrderResponseStatusDto toDtoStatus(Order order);

    OrderResponseOrderItemDto toDtoOrderItem(OrderItem orderitem);
}
