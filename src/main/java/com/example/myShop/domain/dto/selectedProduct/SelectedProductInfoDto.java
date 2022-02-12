package com.example.myShop.domain.dto.selectedProduct;

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
public class SelectedProductInfoDto {
    Integer id;
    Integer count;
    BigDecimal price;

    Integer goodsId;
    Integer orderId;
}