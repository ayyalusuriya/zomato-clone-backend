package com.zomato.repository;

import com.zomato.entity.Cart;
import com.zomato.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserIdAndStatus(Long userId, CartStatus status);

}