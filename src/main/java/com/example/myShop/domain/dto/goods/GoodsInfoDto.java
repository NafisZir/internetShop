package com.example.myShop.domain.dto.goods;

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
public class GoodsInfoDto {
    int id;
    String name;
    int price;
    int availability;
    String image;

    String producerName;
    int categoryID;
}
