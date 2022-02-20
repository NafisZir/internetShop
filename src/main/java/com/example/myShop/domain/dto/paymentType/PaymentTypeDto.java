package com.example.myShop.domain.dto.paymentType;

import com.example.myShop.domain.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "PaymentType", description = "Payment type of order")
public class PaymentTypeDto {
    @Schema(description = "Payment type name",
            required = true)
    PaymentType paymentType;
}
