package com.example.myShop.domain.dto.producer;

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
public class ProducerDto {
    int id;
    String name;
    String country;

    List<Goods> goods;
}
