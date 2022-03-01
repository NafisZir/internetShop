package com.example.myShop.controller;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author nafis
 * @since 19.02.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "auth")
@Tag(name = "Authorization", description = "Authorization of users")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Bad request")
@ApiResponse(responseCode = "401", description = "Authorization of user failed")
public class AuthController {
    private final AuthService authService;

    @Operation(description = "Login user")
    @ApiResponse(responseCode = "200", description = "User login successfully")
    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(description = "Sign up user")
    @ApiResponse(responseCode = "200", description = "User sign up successfully")
    @PostMapping("sign-up")
    public String signUp(@RequestBody UserCreateDto userCreateDto) {
        return authService.signUp(userCreateDto);
    }
}
