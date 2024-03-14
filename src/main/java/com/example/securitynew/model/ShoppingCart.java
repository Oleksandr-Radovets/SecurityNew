package com.example.securitynew.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@SQLDelete(sql = "UPDATE shopping_cart SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinTable(name = "shoppingCart_users", joinColumns = @JoinColumn(name = "shoppingCart_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "shoppingCart_cartItems", joinColumns = @JoinColumn(name = "shoppingCart_id"),
            inverseJoinColumns = @JoinColumn(name = "cartItems_id"))
    private Set<CartItem> cartItems = new HashSet<>();
    private boolean isDeleted;
}
