package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.selectedProduct.SelectedProductCreateDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductUpdateDto;
import com.example.myShop.domain.entity.SelectedProduct;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author nafis
 * @since 10.02.2022
 */

@Mapper
public interface SelectedProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "goods", ignore = true)
    @Mapping(target = "price", ignore = true)
    SelectedProduct fromCreateDto(SelectedProductCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "goods", ignore = true)
    @Mapping(target = "price", ignore = true)
    SelectedProduct fromUpdateDto(SelectedProductUpdateDto source);

    @Mapping(target = "order.userId", source = "order.user.id")
    @Mapping(target = "order.receiveId", source = "order.receiving.id")
    @Mapping(target = "goods.producerId", source = "goods.producer.id")
    @Mapping(target = "goods.categoryId", source = "goods.category.id")
    SelectedProductDto toDto(SelectedProduct source);

    @Mapping(target = "goodsId", source = "goods.id")
    @Mapping(target = "orderId", source = "order.id")
    SelectedProductInfoDto toSelectedProductInfoDto(SelectedProduct source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    SelectedProduct merge(@MappingTarget SelectedProduct target, SelectedProduct source);
}
