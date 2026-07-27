package com.zomato.service;

import com.zomato.dto.LoginRequest;
import com.zomato.dto.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);

    String login(LoginRequest request);

}