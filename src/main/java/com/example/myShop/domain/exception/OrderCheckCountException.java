package com.example.myShop.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 18.01.2022
 */

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class OrderCheckCountException extends RuntimeException{
    public OrderCheckCountException(Integer id){
        super("Count in the order more than quantity of the goods with id: " + id);
    }
}
