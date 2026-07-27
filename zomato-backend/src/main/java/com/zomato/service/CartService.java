package com.zomato.service;

import com.zomato.dto.CartRequest;
import com.zomato.dto.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    List<CartResponse> getUserCart(Long userId);

    CartResponse updateQuantity(Long cartId, Integer quantity);

    void removeFromCart(Long cartId);

    Double getCartTotal(Long userId);

}