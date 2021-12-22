package com.example.myShop.domain.dto.payment;

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
public class PaymentInfoDto {
    int id;
    String payMethod;
}
