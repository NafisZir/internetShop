package com.example.myShop.domain.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 21.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class PaymentCreateDto {
    @NotBlank(message = "{payment.payMethod.empty}")
    @Size(max = 20)
    String payMethod;
}
