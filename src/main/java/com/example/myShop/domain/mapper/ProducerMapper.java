package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.entity.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface ProducerMapper {
    @Mapping(target = "goods", ignore = true)
    Producer fromDto(ProducerDto source);

    ProducerDto toDto(Producer source);

    ProducerInfoDto toInfoDto(Producer source);
}
