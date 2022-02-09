package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.receiving.ReceivingCreateDto;
import com.example.myShop.domain.dto.receiving.ReceivingDto;
import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.receiving.ReceivingUpdateDto;
import com.example.myShop.domain.entity.Receiving;
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
public interface ReceivingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Receiving fromCreateDto(ReceivingCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Receiving fromUpdateDto(ReceivingUpdateDto source);

    ReceivingDto toDto(Receiving source);

    ReceivingInfoDto toInfoDto(Receiving source);

    @Mapping(target = "orders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Receiving merge(@MappingTarget Receiving target, Receiving source);
}
