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
@Schema(name = "GoodsUpdate", description = "Fields of goods that can be updated")
public class GoodsUpdateDto {
        @Schema(description = "Goods name")
        String name;
        @Schema(description = "Price of goods")
        BigDecimal price;
        @Schema(description = "Count of goods",
                minimum = "0",
                maximum = "9223372036854775807L")
        long count;
        @Schema(description = "Image url of goods")
        String imageUrl;
}
