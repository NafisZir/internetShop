package com.example.myShop.domain.exception;

import com.example.myShop.domain.enums.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 18.02.2022
 */

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class RequiredArgsException extends RuntimeException{
    public RequiredArgsException(OrderStatus oldStatus, OrderStatus newStatus){
        super("If OrderStatus changed from "+ oldStatus +" to "+ newStatus +
                "it's necessary to init PaymentType and Receiving");
    }
}
