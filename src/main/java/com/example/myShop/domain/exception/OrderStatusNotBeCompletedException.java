package com.example.myShop.domain.exception;

/**
 * @author nafis
 * @since 18.02.2022
 */
public class OrderStatusNotBeCompletedException extends RuntimeException{
    public OrderStatusNotBeCompletedException(){
        super("OrderStatus will not completed, because BillStatus isn't completed");
    }
}
