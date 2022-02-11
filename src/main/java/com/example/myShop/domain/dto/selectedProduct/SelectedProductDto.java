package com.example.myShop.domain.dto.selectedProduct;

import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.order.OrderInfoDto;
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
public class SelectedProductDto {
    Integer id;
    Integer count;
    BigDecimal price;

    GoodsInfoDto goods;
    OrderInfoDto order;
}
