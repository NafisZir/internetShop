package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.StatusDto;
import com.example.myShop.domain.dto.StatusNotIdDto;
import com.example.myShop.domain.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface StatusMapper {
    @Mapping(target = "id", ignore = true)
    Status fromNotIdDto(StatusNotIdDto source);

    StatusDto toDto(Status source);
}
