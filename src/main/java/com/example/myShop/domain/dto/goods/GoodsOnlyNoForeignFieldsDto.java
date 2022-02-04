package com.example.myShop.domain.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 04.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class GoodsOnlyNoForeignFieldsDto {
    Integer id;
    String name;
    BigDecimal price;
    Long availability;
    String imageUrl;
}
