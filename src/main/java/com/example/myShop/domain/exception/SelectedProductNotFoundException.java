package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author nafis
 * @since 10.02.2022
 */

@ResponseStatus(value = NOT_FOUND)
public class SelectedProductNotFoundException extends RuntimeException{
    public SelectedProductNotFoundException(Integer id){
        super("Selected_product not found: " + id);
    }
}
