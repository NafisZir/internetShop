package com.example.myShop.repository;

import com.example.myShop.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
