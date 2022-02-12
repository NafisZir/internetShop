package com.example.myShop.controller;

import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.dto.user.UserDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.dto.user.UserUpdateDto;
import com.example.myShop.domain.exception.UserNotFoundException;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(userService::getAndInitialize)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public UserInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(userService::getAndInitialize)
                .map(userMapper::toInfoDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping()
    public Page<UserDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(userService::getAndInitializeAll)
                .map(it -> it.map(userMapper::toDto))
                .orElseThrow();
    }

    @PostMapping()
    public UserDto create(@Valid @RequestBody UserCreateDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromCreateDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable("id") Integer id, @RequestBody UserUpdateDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate -> userService.update(toUpdate, id))
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable(name = "userId") Integer id){
        userService.delete(id);
    }
}
