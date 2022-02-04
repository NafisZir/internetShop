package com.example.myShop.domain.dto.order;

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
public class OrderInfoDto {
    int id;
    int count;
    BigDecimal price;

    int goodsId;
    int userId;
    String status;
    int receiveId;
    int payId;
}
