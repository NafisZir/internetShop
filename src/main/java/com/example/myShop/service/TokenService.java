package com.example.myShop.service;

import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.exception.InvalidTokenException;

/**
 * @author nafis
 * @since 19.02.2022
 */
public interface TokenService {
    /**
     * Generate a new token for user
     * @param user user that does login or sign up
     * @return Token as String
     */
    String generateToken(User user);

    /**
     * Find claims using token and extract from there username if exists
     * @param token Token of user
     * @return username (email of user)
     * @throws InvalidTokenException incorrect token or time of token over
     */
    String extractUsernameAndValidate(String token);
}
