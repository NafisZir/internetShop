package com.example.myShop.domain.exception;

/**
 * @author nafis
 * @since 19.02.2022
 */
public class AuthUserNotFoundException extends RuntimeException{
    public AuthUserNotFoundException(String username){
        super("User with username: "+ username +" not found");
    }
}
