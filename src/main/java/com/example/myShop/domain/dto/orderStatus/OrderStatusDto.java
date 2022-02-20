package com.example.myShop.domain.dto.orderStatus;

import com.example.myShop.domain.enums.OrderStatus;
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
@Schema(name = "OrderStatus", description = "Order status name")
public class OrderStatusDto {
    @Schema(description = "Order status name",
            required = true)
    OrderStatus orderStatus;
}
