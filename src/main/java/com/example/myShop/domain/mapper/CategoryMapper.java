package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.category.CategoryCreateDto;
import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.category.CategoryUpdateDto;
import com.example.myShop.domain.entity.Category;
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
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "goods", ignore = true)
    Category fromCreateDto(CategoryCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "goods", ignore = true)
    Category fromUpdateDto(CategoryUpdateDto source);

    CategoryDto toDto(Category source);

    CategoryInfoDto toInfoDto(Category source);

    @Mapping(target = "goods", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Category merge(@MappingTarget Category target, Category source);
}
