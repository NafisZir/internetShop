package com.example.myShop.service.impl;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author nafis
 * @since 05.02.2022
 */

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Override
    public Set<OrderStatus> getAll() {
        return EnumSet.allOf(OrderStatus.class);
    }

    @Override
    public CollectionWrapper<OrderStatus> getAllAndWrap() {
        return new CollectionWrapper<>(EnumSet.allOf(OrderStatus.class));
    }
}
