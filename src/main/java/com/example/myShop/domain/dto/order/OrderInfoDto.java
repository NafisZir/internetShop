package com.example.myShop.domain.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author nafis
 * @since 22.12.2021
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
@Schema(name = "OrderInfo", description = "Info about order of user")
public class OrderInfoDto {
    @Schema(description = "Order id",
            required = true)
    Integer id;
    @Schema(description = "Total price of order",
            required = true)
    BigDecimal totalPrice;

    @Schema(description = "User id of order",
            required = true)
    Integer userId;
    @Schema(description = "Receiving id of order")
    Integer receiveId;
    @Schema(description = "Payment type of order")
    String paymentType;
    @Schema(description = "Status of order",
            required = true)
    String orderStatus;
    @Schema(description = "Billing status of order",
            required = true)
    String billStatus;
}
