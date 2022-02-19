package com.example.myShop.controller;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nafis
 * @since 19.02.2022
 */

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("sign-up")
    public String signUp(@RequestBody UserCreateDto userCreateDto) {
        return authService.signUp(userCreateDto);
    }
}
