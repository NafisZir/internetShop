package com.example.myShop.domain.dto.paymentType;

import com.example.myShop.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class PaymentTypeDto {
    PaymentType paymentType;
}
