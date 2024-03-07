package com.example.securitynew.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    @Column(name = "shoppingCart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShoppingCart;
    @OneToOne
    @JoinTable(name = "shoppingCart_users",
    joinColumns = @JoinColumn(name = "shoppingCart_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "shoppingCart_cartItems",
    joinColumns = @JoinColumn(name = "shoppingCart_id"),
    inverseJoinColumns = @JoinColumn(name = "cartItems_id"))
    private Set<CartItem> cartItemSet;
}
