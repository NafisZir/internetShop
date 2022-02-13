package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.enums.PaymentType;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "receiving", ignore = true)
    @Mapping(target = "selectedProducts", ignore = true)
//    @Mapping(target = "paymentType", qualifiedByName = "paymentToEnum", resultType = PaymentType.class)
//    @Mapping(target = "billStatus", qualifiedByName = "billStatusToEnum", resultType = BillStatus.class)
//    @Mapping(target = "orderStatus", qualifiedByName = "orderStatusToEnum", resultType = OrderStatus.class)
    Order fromUpdateDto(OrderUpdateDto source);

    OrderDto toDto(Order source);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "receiveId", source = "receiving.id")
//    @Mapping(target = "paymentType", source = "paymentType.payment")
//    @Mapping(target = "billStatus", source = "billStatus.status")
//    @Mapping(target = "orderStatus", source = "orderStatus.status")
    OrderInfoDto toInfoDto(Order source);

    @Mapping(target = "selectedProducts", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Order merge(@MappingTarget Order target, Order source);
}
