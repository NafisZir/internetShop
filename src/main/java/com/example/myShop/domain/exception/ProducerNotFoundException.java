package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 28.12.2021
 */

@ResponseStatus(value = NOT_FOUND)
public class ProducerNotFoundException extends RuntimeException{
    public ProducerNotFoundException(Integer id){
        super("Producer not found: " + id);
    }
}
