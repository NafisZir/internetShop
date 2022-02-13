package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.OrderStatus;

import java.util.Set;

/**
 * @author nafis
 * @since 05.02.2022
 */

public interface OrderStatusService {
    Set<OrderStatus> getAll();

    CollectionWrapper<OrderStatus> getAllAndWrap();
}
