package com.example.myShop.service;

import com.example.myShop.domain.entity.Payment;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */
public interface PaymentService {
    Payment get(String id);

    List<Payment> getPayments();

    void create(Payment payment);

    void update(Payment payment);

    void delete(String id);
}
