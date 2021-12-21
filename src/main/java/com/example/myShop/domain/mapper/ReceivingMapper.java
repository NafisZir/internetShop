package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.ReceivingDto;
import com.example.myShop.domain.dto.ReceivingNotIdDto;
import com.example.myShop.domain.entity.Receiving;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface ReceivingMapper {
    @Mapping(target = "id", ignore = true)
    Receiving fromNotIdDto(ReceivingNotIdDto source);

    ReceivingDto toDto(Receiving source);
}
