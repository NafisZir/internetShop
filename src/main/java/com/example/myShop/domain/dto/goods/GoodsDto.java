package com.example.myShop.domain.dto.goods;

import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
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
public class GoodsDto {
    Integer id;
    String name;
    BigDecimal price;
    Long count;
    String imageUrl;

    ProducerInfoDto producer;
    CategoryInfoDto category;
}
