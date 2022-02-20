package com.example.myShop.domain.dto.selectedProduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "SelectedProductInfo", description = "Info about products selected by user")
public class SelectedProductInfoDto {
    @Schema(description = "SelectedProduct id",
            required = true)
    Integer id;
    @Schema(description = "Count of goods in selected product",
            required = true)
    Integer count;
    @Schema(description = "Summary price of one or several goods in selected product",
            required = true)
    BigDecimal price;

    @Schema(description = "Goods id of selected product",
            required = true)
    Integer goodsId;
    @Schema(description = "Order id to which selected product belongs",
            required = true)
    Integer orderId;
}
