package com.example.myShop.service;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.exception.AuthUserNotFoundException;
import com.example.myShop.domain.exception.InvalidUserPasswordException;

/**
 * @author nafis
 * @since 19.02.2022
 */
public interface AuthService {
    /**
     * Search username and password from database and if searching was successfully
     * allows user to be authorized.
     * @param loginRequest contains username and password
     * @return Generated token as String
     * @throws InvalidUserPasswordException password isn't correct
     * @throws AuthUserNotFoundException user with username didn't find
     */
    String login(LoginRequest loginRequest);

    /**
     * Create a new user and save it to database with encoding password.
     * @param userCreateDto contains data about user
     * @return Generated token as String
     */
    String signUp(UserCreateDto userCreateDto);
}
