package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.order.OrderCreateDto;
import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.entity.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "goods", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "receiving", ignore = true)
    @Mapping(target = "payment", ignore = true)
    Order fromCreateDto(OrderCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "goods", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "receiving", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "status", ignore = true)
    Order fromUpdateDto(OrderUpdateDto source);

    OrderDto toDto(Order source);

    @Mapping(target = "goodsId", source = "goods.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", source = "status.status")
    @Mapping(target = "receiveId", source = "receiving.id")
    @Mapping(target = "payId", source = "payment.id")
    OrderInfoDto toInfoDto(Order source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Order merge(@MappingTarget Order target, Order source);
}
