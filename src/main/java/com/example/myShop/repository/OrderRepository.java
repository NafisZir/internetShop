package com.example.myShop.repository;

import com.example.myShop.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findAllByUserId(Pageable pageable, Integer userId);

    Page<Order> findAllByReceivingId(Pageable pageable, Integer receivingId);
}
