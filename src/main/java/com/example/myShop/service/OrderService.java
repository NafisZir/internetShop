package com.example.myShop.service;

import com.example.myShop.domain.entity.Order;

import java.security.Principal;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface OrderService {
    Order get(Integer id);

    Order create(Order order, Integer goodId, Principal principal);

    Order update(Integer id, Order order);

    void delete(Integer id);

    List<Order> getOrdersByClientID(Integer id);

    List<Order> getOrders();

    List<Order> getOrdersByPayId(Integer id);

    List<Order> getOrdersByReceiveId(Integer id);
}
