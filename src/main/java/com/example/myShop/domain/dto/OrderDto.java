package com.example.myShop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

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
    int goodsID;
    int clientID;
    int statusId;
    int count;
    int price;
    int receiveID;
    int payId;
}
