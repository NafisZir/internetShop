package com.example.myShop.service;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.example.myShop.domain.dto.user.UserCreateDto;

/**
 * @author nafis
 * @since 19.02.2022
 */
public interface AuthService {
    String login(LoginRequest loginRequest);

    String signUp(UserCreateDto userCreateDto);
}
