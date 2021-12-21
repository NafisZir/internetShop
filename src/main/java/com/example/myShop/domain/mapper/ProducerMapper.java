package com.example.myShop.domain.mapper;

import com.example.myShop.domain.dto.ProducerDto;
import com.example.myShop.domain.entity.Producer;
import org.mapstruct.Mapper;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Mapper
public interface ProducerMapper {
    Producer fromDto(ProducerDto source);

    ProducerDto toDto(Producer source);
}
