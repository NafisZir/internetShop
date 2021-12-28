package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 28.12.2021
 */

@ResponseStatus(value = NOT_FOUND)
public class ReceivingNotFoundException extends RuntimeException{
    public ReceivingNotFoundException(Integer id){
        super("Receiving not found: " + id);
    }
}
