package com.example.myShop.domain.dto.goods;

import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
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
@Schema(name = "Goods", description = "Full info about goods")
public class GoodsDto {
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

    @Schema(description = "Producer info of goods",
            required = true)
    ProducerInfoDto producer;
    @Schema(description = "Category info of goods",
            required = true)
    CategoryInfoDto category;
}
