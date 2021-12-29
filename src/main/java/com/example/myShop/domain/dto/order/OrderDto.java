package com.example.myShop.domain.dto.order;

import com.example.myShop.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class OrderDto {
    int id;
    int count;
    BigDecimal price;

    Goods goods;
    User user;
    Status status;
    Receiving receiving;
    Payment payment;
}
