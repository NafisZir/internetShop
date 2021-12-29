package com.example.myShop.domain.dto.order;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Setter
@Getter
@Jacksonized
@AllArgsConstructor
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
