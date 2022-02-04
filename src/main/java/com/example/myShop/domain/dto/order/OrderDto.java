package com.example.myShop.domain.dto.order;

import com.example.myShop.domain.dto.goods.GoodsOnlyNoForeignFieldsDto;
import com.example.myShop.domain.dto.payment.PaymentInfoDto;
import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.entity.Status;
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
    Integer id;
    Integer count;
    BigDecimal price;

    GoodsOnlyNoForeignFieldsDto goods;
    UserInfoDto user;
    Status status;
    ReceivingInfoDto receiving;
    PaymentInfoDto payment;
}
