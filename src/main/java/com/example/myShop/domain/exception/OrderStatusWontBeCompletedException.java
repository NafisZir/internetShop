package com.example.myShop.domain.exception;

/**
 * @author nafis
 * @since 18.02.2022
 */
public class OrderStatusWontBeCompletedException extends RuntimeException{
    public OrderStatusWontBeCompletedException(){
        super("OrderStatus will not completed, because BillStatus isn't completed");
    }
}
