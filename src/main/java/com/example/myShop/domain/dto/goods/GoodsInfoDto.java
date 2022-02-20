package com.example.myShop.domain.dto.goods;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "GoodsInfo", description = "Info about goods")
public class GoodsInfoDto {
    @Schema(description = "Goods id",
            required = true)
    Integer id;
    @Schema(description = "Goods name",
            required = true)
    String name;
    @Schema(description = "Price of goods",
            required = true)
    BigDecimal price;
    @Schema(description = "Count of goods",
            required = true,
            minimum = "0",
            maximum = "9223372036854775807L")
    Long count;
    @Schema(description = "Image url of goods",
            required = true)
    String imageUrl;

    @Schema(description = "Producer id of goods",
            required = true)
    Integer producerId;
    @Schema(description = "Category id of goods",
            required = true)
    Integer categoryId;
}
