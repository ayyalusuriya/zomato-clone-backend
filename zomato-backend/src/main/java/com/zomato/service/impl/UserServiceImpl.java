package com.zomato.service.impl;

import com.zomato.dto.LoginRequest;
import com.zomato.dto.RegisterRequest;
import com.zomato.entity.User;
import com.zomato.enums.Role;
import com.zomato.repository.UserRepository;
import com.zomato.security.JwtUtil;
import com.zomato.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail()))
            return "Email already exists";

        if (userRepository.existsByPhone(request.getPhone()))
            return "Phone already exists";

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();

        userRepository.save(user);

        return "Registration Successful";
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Password");

        return jwtUtil.generateToken(user);
    }
}