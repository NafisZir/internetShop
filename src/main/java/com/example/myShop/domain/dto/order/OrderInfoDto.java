package com.example.myShop.domain.dto.order;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

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
    int price;

    int goodsId;
    int userId;
    int statusId;
    int receiveId;
    int payId;
}
