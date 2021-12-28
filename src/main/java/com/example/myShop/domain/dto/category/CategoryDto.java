package com.example.myShop.domain.dto.category;

import com.example.myShop.domain.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

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
public class CategoryDto {
    int id;
    String name;

    List<Goods> goods;
}
