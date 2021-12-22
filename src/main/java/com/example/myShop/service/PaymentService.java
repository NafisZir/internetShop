package com.example.myShop.service;

import com.example.myShop.domain.entity.Payment;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface PaymentService {
    Payment get(Integer id);

    Payment create(Payment payment);

    Payment update(Payment payment, Integer id);

    void delete(Integer id);
}
