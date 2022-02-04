package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Status;
import org.springframework.stereotype.Component;

/**
 * @author nafis
 * @since 01.02.2022
 */

@Component
public class OrderUpdateMapper {

    public Order fromUpdateDto(OrderUpdateDto dto){
        Status status = Status.decode(dto.getStatus());

        Order order = new Order();
        order.setStatus(status);

        return order;
    }
}
