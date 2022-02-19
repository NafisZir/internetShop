package com.example.myShop.service;

import com.example.myShop.domain.entity.User;

/**
 * @author nafis
 * @since 19.02.2022
 */
public interface TokenService {
    String generateToken(User user);

    String extractUsernameAndValidate(String token);
}
