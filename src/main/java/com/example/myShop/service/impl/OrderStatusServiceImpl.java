package com.example.myShop.service.impl;

import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author nafis
 * @since 05.02.2022
 */

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Override
    public List<String> getAll() {
        EnumSet<OrderStatus> enumSet = EnumSet.allOf(OrderStatus.class);
        List<String> result = new ArrayList<>();

        for(OrderStatus orderStatus : enumSet){
            result.add(orderStatus.getStatus());
        }

        return result;
    }
}
