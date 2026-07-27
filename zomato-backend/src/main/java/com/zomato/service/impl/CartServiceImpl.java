package com.zomato.service.impl;

import com.zomato.dto.CartRequest;
import com.zomato.dto.CartResponse;
import com.zomato.entity.Cart;
import com.zomato.entity.Food;
import com.zomato.entity.User;
import com.zomato.enums.CartStatus;
import com.zomato.repository.CartRepository;
import com.zomato.repository.FoodRepository;
import com.zomato.repository.UserRepository;
import com.zomato.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Override
    public CartResponse addToCart(CartRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new RuntimeException("Food not found"));

        double total = food.getPrice() * request.getQuantity();

        Cart cart = Cart.builder()
                .user(user)
                .food(food)
                .quantity(request.getQuantity())
                .price(food.getPrice())
                .totalPrice(total)
                .status(CartStatus.ACTIVE)
                .build();

        return map(cartRepository.save(cart));
    }

    @Override
    public List<CartResponse> getUserCart(Long userId) {

        return cartRepository.findByUserIdAndStatus(userId, CartStatus.ACTIVE)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CartResponse updateQuantity(Long cartId, Integer quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.setQuantity(quantity);
        cart.setTotalPrice(cart.getPrice() * quantity);

        return map(cartRepository.save(cart));
    }

    @Override
    public void removeFromCart(Long cartId) {

        cartRepository.deleteById(cartId);

    }

    @Override
    public Double getCartTotal(Long userId) {

        return cartRepository.findByUserIdAndStatus(userId, CartStatus.ACTIVE)
                .stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();
    }

    private CartResponse map(Cart cart) {

        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .userName(cart.getUser().getFullName())
                .foodId(cart.getFood().getId())
                .foodName(cart.getFood().getFoodName())
                .quantity(cart.getQuantity())
                .price(cart.getPrice())
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}