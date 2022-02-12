package com.example.myShop.service.impl;

import com.example.myShop.domain.enums.PaymentType;
import com.example.myShop.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
public class PaymentServiceImp implements PaymentService {

    @Override
    public List<String> getAll() {
        EnumSet<PaymentType> enumSet = EnumSet.allOf(PaymentType.class);
        List<String> result = new ArrayList<>();

        for(PaymentType paymentType : enumSet){
            result.add(paymentType.getPayment());
        }

        return result;
    }
}
