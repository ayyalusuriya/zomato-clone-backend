package com.zomato.controller;

import com.zomato.dto.AuthResponse;
import com.zomato.dto.LoginRequest;
import com.zomato.dto.RegisterRequest;
import com.zomato.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        return new AuthResponse(userService.register(request));

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return new AuthResponse(userService.login(request));

    }

}