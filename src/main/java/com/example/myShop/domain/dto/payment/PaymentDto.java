package com.example.myShop.domain.dto.payment;

import com.example.myShop.domain.entity.Order;
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
public class PaymentDto {
    int id;
    String payMethod;

    List<Order> orders;
}
