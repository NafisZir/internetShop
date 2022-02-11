package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 10.02.2022
 */

@ResponseStatus(value = NOT_FOUND)
public class BankCardNotFoundException extends RuntimeException{
    public BankCardNotFoundException(Integer id){
        super("Bank card not found: " + id);
    }
}
