package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.CategoryDto;
import com.example.myShop.domain.dto.CategoryNotIdDto;
import com.example.myShop.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category fromNotIdDto(CategoryNotIdDto source);

    CategoryDto toDto(Category source);
}
