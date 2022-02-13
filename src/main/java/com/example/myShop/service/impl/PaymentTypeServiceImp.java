package com.example.myShop.service.impl;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.PaymentType;
import com.example.myShop.service.PaymentTypeService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
public class PaymentTypeServiceImp implements PaymentTypeService {

    @Override
    public Set<PaymentType> getAll() {
        return EnumSet.allOf(PaymentType.class);
    }

    @Override
    public CollectionWrapper<PaymentType> getAllAndWrap() {
        return new CollectionWrapper<>(EnumSet.allOf(PaymentType.class));
    }
}
