package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Payment;
import com.example.myShop.repository.PaymentRepository;
import com.example.myShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment get(Integer id){
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment create(Payment payment){
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment, Integer id) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Integer id){
        paymentRepository.deleteById(id);
    }
}
