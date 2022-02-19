package com.example.myShop.domain.exception;

/**
 * @author nafis
 * @since 19.02.2022
 */
public class InvalidUserPasswordException extends RuntimeException{
    public InvalidUserPasswordException(String username){
        super("Incorrect password of user with username: " + username);
    }
}
