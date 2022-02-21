package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.OrderStatus;

import java.util.Set;

/**
 * @author nafis
 * @since 05.02.2022
 */

public interface OrderStatusService {
    /**
     * Find from OrderStatus enum all order statuses
     * @return set of all order statuses
     */
    Set<OrderStatus> getAll();

    /**
     *Find from OrderStatus enum all order statuses and wrap to CollectionWrapper
     * @return wrapper of all order statuses
     */
    CollectionWrapper<OrderStatus> getAllAndWrap();
}
