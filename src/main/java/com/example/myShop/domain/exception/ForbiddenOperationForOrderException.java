package com.example.myShop.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 07.02.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ForbiddenOperationForOrderException extends RuntimeException{
    public ForbiddenOperationForOrderException(String message){
        super(message);
    }
}
