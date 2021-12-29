package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.entity.Producer;
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
public interface ProducerMapper {
    @Mapping(target = "goods", ignore = true)
    @Mapping(target = "id", ignore = true)
    Producer fromDto(ProducerDto source);

    ProducerDto toDto(Producer source);

    ProducerInfoDto toInfoDto(Producer source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Producer merge(@MappingTarget Producer target, Producer source);
}
