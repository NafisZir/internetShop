package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.status.StatusDto;
import com.example.myShop.domain.dto.status.StatusCreateDto;
import com.example.myShop.domain.dto.status.StatusInfoDto;
import com.example.myShop.domain.dto.status.StatusUpdateDto;
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
    @Mapping(target = "orders", ignore = true)
    Status fromCreateDto(StatusCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Status fromUpdateDto(StatusUpdateDto source);

    StatusDto toDto(Status source);

    StatusInfoDto toInfoDto(Status source);
}
