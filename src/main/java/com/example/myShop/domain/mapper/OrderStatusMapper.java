package com.example.myShop.domain.mapper;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.dto.CollectionWrapperDto;
import com.example.myShop.domain.dto.orderStatus.OrderStatusDto;
import com.example.myShop.domain.enums.OrderStatus;
import org.mapstruct.Mapper;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Mapper
public interface OrderStatusMapper {
    OrderStatusDto toDto(OrderStatus orderStatus);

    CollectionWrapperDto<OrderStatusDto> toWrapper(CollectionWrapper<OrderStatus> collection);
}
