package com.example.myShop.service;

import com.example.myShop.domain.entity.Payment;

import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface PaymentService {
    Payment get(Integer id);

    Map<String, Object> getAll(int page, int size);

    Payment create(Payment payment);

    Payment update(Payment payment, Integer id);

    void delete(Integer id);
}
