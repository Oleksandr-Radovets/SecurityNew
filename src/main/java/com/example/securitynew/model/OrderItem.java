package com.example.securitynew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinTable(name = "order_items_orders",
            joinColumns = @JoinColumn(name = "order_items_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "order_items_books",
            joinColumns = @JoinColumn(name = "order_items_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Book book;
    private int quantity;
    private BigDecimal price;
}
