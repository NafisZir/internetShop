package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.order.OrderDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
import com.example.myShop.domain.dto.order.OrderUpdateDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.SelectedProduct;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper(componentModel = "spring", uses = {SelectedProductMapper.class})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "receiving", ignore = true)
    @Mapping(target = "selectedProducts", ignore = true)
    Order fromUpdateDto(OrderUpdateDto source);

    OrderDto toDto(Order source);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "receiveId", source = "receiving.id")
    OrderInfoDto toInfoDto(Order source);

    @Mapping(target = "selectedProducts", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Order merge(@MappingTarget Order target, Order source);

    List<SelectedProductInfoDto> toSelectedProductInfoDtoList(List<SelectedProduct> list);
}
