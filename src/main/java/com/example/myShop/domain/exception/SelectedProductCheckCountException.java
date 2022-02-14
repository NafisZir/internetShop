package com.example.myShop.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nafis
 * @since 18.01.2022
 */

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class SelectedProductCheckCountException extends RuntimeException{
    public SelectedProductCheckCountException(Long selProductCount, Long goodsCount){
        super("Count in the SelectedProduct(" +selProductCount+
                ") more than quantity of the goods(" + goodsCount + ")");
    }
}
