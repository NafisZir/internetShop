package com.example.myShop.domain.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class GoodsInfoDto {
    Integer id;
    String name;
    BigDecimal price;
    Long count;
    String imageUrl;

    Integer producerId;
    Integer categoryId;
}
