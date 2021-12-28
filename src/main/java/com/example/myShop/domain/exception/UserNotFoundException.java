package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 28.12.2021
 */

@ResponseStatus(value = NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super("User not found: " + id);
    }
}
