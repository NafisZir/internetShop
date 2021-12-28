package com.example.myShop.domain.dto.goods;

import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class GoodsUpdateDto {
        String name;
        int price;
        int availability;
        String imageUrl;
}
