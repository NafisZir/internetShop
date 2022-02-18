package com.example.myShop.domain.exception;

import com.example.myShop.domain.enums.BillStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 18.02.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class IllegalBillStatusException extends RuntimeException{
    public IllegalBillStatusException(BillStatus newStatus, BillStatus oldStatus){
        super("This status " + newStatus + " doesn't should  be after status " + oldStatus);
    }
}
