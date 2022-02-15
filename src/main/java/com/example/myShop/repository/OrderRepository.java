package com.example.myShop.repository;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findFirstByUserIdAndOrderStatusIn(Integer userId, Collection<OrderStatus> statuses);

    Order findByUserIdAndOrderStatus(Integer userId, OrderStatus status);

    Page<Order> findAllByUserId(Pageable pageable, Integer userId);

    Page<Order> findAllByReceivingId(Pageable pageable, Integer receivingId);
}
