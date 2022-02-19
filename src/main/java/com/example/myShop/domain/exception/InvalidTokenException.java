package com.example.myShop.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 19.02.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super("Expired token or incorrect data");
    }
}
