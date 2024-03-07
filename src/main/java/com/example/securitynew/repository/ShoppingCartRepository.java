package com.example.securitynew.repository;

import com.example.securitynew.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("FROM ShoppingCart sc WHERE sc.user.getId :=userId")
    ShoppingCart findByUserId(@Param("userId") Long userId);
}
