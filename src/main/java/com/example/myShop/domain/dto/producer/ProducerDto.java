package com.example.myShop.domain.dto.producer;

import com.example.myShop.domain.dto.goods.GoodsOnlyNoForeignFieldsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class ProducerDto {
    Integer id;
    String name;
    String country;

    List<GoodsOnlyNoForeignFieldsDto> goods;
}
