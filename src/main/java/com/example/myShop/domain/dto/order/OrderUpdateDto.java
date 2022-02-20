package com.example.myShop.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "OrderUpdate", description = "Fields of order that can be updated")
public class OrderUpdateDto {
    @Schema(description = "Payment type of order")
    String paymentType;
    @Schema(description = "Billing status of order")
    String billStatus;
    @Schema(description = "Status of order")
    String orderStatus;
}
