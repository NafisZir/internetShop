package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

/**
 * @author nafis
 * @since 11.02.2022
 */

@ResponseStatus(value = NOT_ACCEPTABLE)
public class SelectedProductDeleteException extends RuntimeException{
    public SelectedProductDeleteException(String message){
        super(message);
    }
}
