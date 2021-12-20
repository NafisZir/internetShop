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

    String create(Order order, Integer goodId);

    void update(Order order);

    void delete(Integer id);

    List<Order> getOrdersByClientID(Integer id);

    List<Order> getOrdersByGoodsID(Integer id);

    List<Order> getOrders();

    List<Order> getOrdersByPayMethod(String id);

    List<Order> getOrdersByReceiveId(Integer id);

    List<Order> getOrdersByStatusName(String id);
}
