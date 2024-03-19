package com.example.securitynew.dto.order;

import com.example.securitynew.model.OrderItem;
import com.example.securitynew.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal total;
    private LocalDateTime orderData;
    private String shippingAddress;
    private Set<OrderItem> orderItemSet;
    private String status;
}
