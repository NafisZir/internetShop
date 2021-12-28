package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 28.12.2021
 */

@ResponseStatus(value = NOT_FOUND)
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Integer id){
        super("Order not found: " + id);
    }
}
