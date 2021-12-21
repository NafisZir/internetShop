package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.OrderDto;
import com.example.myShop.domain.dto.OrderNotIdDto;
import com.example.myShop.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    Order fromNotIdDto(OrderNotIdDto source);

    OrderDto toDto(Order source);
}
