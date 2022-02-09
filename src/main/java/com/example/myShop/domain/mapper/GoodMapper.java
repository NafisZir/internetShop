package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.goods.GoodCreateDto;
import com.example.myShop.domain.dto.goods.GoodDto;
import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.entity.Goods;
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
public interface GoodMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "producer", ignore = true)
    @Mapping(target = "category", ignore = true)
    Goods fromCreateDto(GoodCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "producer", ignore = true)
    @Mapping(target = "category", ignore = true)
    Goods fromUpdateDto(GoodsUpdateDto source);

    GoodDto toDto(Goods source);

    @Mapping(target = "producerId", source = "producer.id")
    @Mapping(target = "categoryId", source = "category.id")
    GoodsInfoDto toInfoDto(Goods source);

    @Mapping(target = "orders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Goods merge(@MappingTarget Goods target, Goods source);
}
