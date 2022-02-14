package com.example.myShop.domain.exception;

import com.example.myShop.domain.enums.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 07.02.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class OrderDeleteException extends RuntimeException{
    public OrderDeleteException(OrderStatus status, Integer id){
        super("Delete operation is not acceptable for status: " + status +
                ". Status must be CREATING, CANCELED or COMPLETED. " +
                "Order id: " + id);
    }
}
