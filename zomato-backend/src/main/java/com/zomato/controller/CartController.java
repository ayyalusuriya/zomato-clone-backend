package com.zomato.controller;

import com.zomato.dto.CartRequest;
import com.zomato.dto.CartResponse;
import com.zomato.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartResponse addToCart(@RequestBody CartRequest request) {

        return cartService.addToCart(request);

    }

    @GetMapping("/{userId}")
    public List<CartResponse> getUserCart(@PathVariable Long userId) {

        return cartService.getUserCart(userId);

    }

    @PutMapping("/{cartId}")
    public CartResponse updateQuantity(@PathVariable Long cartId,
                                       @RequestParam Integer quantity) {

        return cartService.updateQuantity(cartId, quantity);

    }

    @DeleteMapping("/{cartId}")
    public String removeFromCart(@PathVariable Long cartId) {

        cartService.removeFromCart(cartId);

        return "Item Removed Successfully";
    }

    @GetMapping("/total/{userId}")
    public Double getCartTotal(@PathVariable Long userId) {

        return cartService.getCartTotal(userId);

    }
}