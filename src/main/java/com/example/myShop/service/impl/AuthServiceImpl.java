package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.security.LoginRequest;
import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.enums.Role;
import com.example.myShop.domain.exception.AuthUserNotFoundException;
import com.example.myShop.domain.exception.InvalidUserPasswordException;
import com.example.myShop.repository.UserRepository;
import com.example.myShop.service.AuthService;
import com.example.myShop.service.TokenService;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author nafis
 * @since 19.02.2022
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserService userService;

    @Override
    public String login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AuthUserNotFoundException(username));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user);
        } else {
            throw new InvalidUserPasswordException(username);
        }
    }

    @Override
    public String signUp(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setPhone(userCreateDto.getPhone());
        user.setRole(Enum.valueOf(Role.class, userCreateDto.getRole()));
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userService.create(user);
        return tokenService.generateToken(user);
    }
}
