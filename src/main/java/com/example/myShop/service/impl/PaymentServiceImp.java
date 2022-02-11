package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Payment;
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
        EnumSet<Payment> enumSet = EnumSet.allOf(Payment.class);
        List<String> result = new ArrayList<>();

        for(Payment payment : enumSet){
            result.add(payment.getPayment());
        }

        return result;
    }
}
