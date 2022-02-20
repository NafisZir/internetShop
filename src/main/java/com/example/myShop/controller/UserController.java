package com.example.myShop.controller;

import com.example.myShop.domain.dto.user.UserCreateDto;
import com.example.myShop.domain.dto.user.UserDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.dto.user.UserUpdateDto;
import com.example.myShop.domain.exception.UserNotFoundException;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
@Tag(name = "User", description = "All basic operations with users")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "User not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(description = "Find user by id")
    @ApiResponse(responseCode = "200", description = "User found")
    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(userService::getAndInitialize)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Operation(description = "Find information about user by id")
    @ApiResponse(responseCode = "200", description = "User found")
    @GetMapping("/info/{id}")
    public UserInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(userService::getAndInitialize)
                .map(userMapper::toInfoDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Operation(description = "Find page of all users")
    @ApiResponse(responseCode = "200", description = "Users found or page is empty")
    @GetMapping()
    public Page<UserDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(userService::getAndInitializeAll)
                .map(it -> it.map(userMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create a new user")
    @ApiResponse(responseCode = "200", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Validation of user data failed")
    @PostMapping()
    public UserDto create(@Valid @RequestBody UserCreateDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromCreateDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Update a created user")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PatchMapping("/{id}")
    public UserDto update(@PathVariable("id") Integer id, @RequestBody UserUpdateDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate -> userService.update(toUpdate, id))
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Operation(description = "Remove user")
    @ApiResponse(responseCode = "204", description = "User removed successfully")
    @ResponseStatus(value = NO_CONTENT)
    @DeleteMapping("{userId}")
    public void delete(@PathVariable(name = "userId") Integer id){
        userService.delete(id);
    }
}
