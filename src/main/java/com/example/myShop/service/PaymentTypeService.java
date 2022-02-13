package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.PaymentType;

import java.util.Set;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface PaymentTypeService {
    Set<PaymentType> getAll();

    CollectionWrapper<PaymentType> getAllAndWrap();
}
