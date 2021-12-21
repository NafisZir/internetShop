package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.GoodDto;
import com.example.myShop.domain.dto.GoodNotIdDto;
import com.example.myShop.domain.entity.Goods;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface GoodMapper {
    @Mapping(target = "id", ignore = true)
    Goods fromNotIdDto(GoodNotIdDto source);

    GoodDto toDto(Goods source);
}
