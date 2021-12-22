package com.example.myShop.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

/**
 * @author nafis
 * @since 22.12.2021
 */
@ResponseStatus(value = NOT_ACCEPTABLE)
public class LinkedGoodsExistsException extends RuntimeException{
    public LinkedGoodsExistsException(Object id, String entity){
        super("This " + entity + " with id: " + id + " has a linked goods");
    }
}
