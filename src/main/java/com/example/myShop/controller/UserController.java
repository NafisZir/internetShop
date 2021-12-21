package com.example.myShop.controller;

import com.example.myShop.domain.dto.UserDto;
import com.example.myShop.domain.dto.UserNotIdDto;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("{id}")
    public UserDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Receiving method not found:" + id));
    }

    @PostMapping()
    public UserDto create(@RequestBody UserNotIdDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromNotIdDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable("id") Integer id,@RequestBody UserNotIdDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromNotIdDto)
                .map(toUpdate -> userService.update(toUpdate, id))
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable(name = "userId") Integer id){
        userService.delete(id);
    }
}
