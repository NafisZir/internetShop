package com.example.myShop.domain.exception;

import com.example.myShop.domain.enums.OrderStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

/**
 * @author nafis
 * @since 11.02.2022
 */

@ResponseStatus(value = NOT_ACCEPTABLE)
public class SelectedProductChangeException extends RuntimeException{
    public SelectedProductChangeException(OrderStatus status){
        super("SelectedProduct is containing in order with status " +
                status + ". It must be: CREATING");
    }
}
