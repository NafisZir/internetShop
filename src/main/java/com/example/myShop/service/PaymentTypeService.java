package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.PaymentType;

import java.util.Set;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface PaymentTypeService {
    /**
     * Find from PaymentType enum all payment types
     * @return set of all payment types
     */
    Set<PaymentType> getAll();

    /**
     * Find from PaymentType enum all payment types and wrap to CollectionWrapper
     * @return wrapper of all payment types
     */
    CollectionWrapper<PaymentType> getAllAndWrap();
}
