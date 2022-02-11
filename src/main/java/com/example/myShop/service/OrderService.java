package com.example.myShop.service;

import com.example.myShop.domain.entity.Order;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author nafis
 * @since 19.12.2021
 */

public interface OrderService {
    Order getAndInitialize(Integer id);

    Map<String, Object> getAndInitializeAll(int page, int size, Integer userId);

    Order create(BigDecimal price, Integer userId);

    Order update(Integer id, Order order);

    void delete(Integer id);
}
