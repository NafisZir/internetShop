package com.example.myShop.domain.exception;

import com.example.myShop.domain.enums.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 18.02.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class IllegalOrderStatusException extends RuntimeException{
    public IllegalOrderStatusException(OrderStatus newStatus, OrderStatus oldStatus){
        super("This status " + newStatus + " doesn't should  be after status " + oldStatus);
    }
}
