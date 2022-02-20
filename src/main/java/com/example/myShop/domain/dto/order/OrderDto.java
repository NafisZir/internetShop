package com.example.myShop.domain.dto.order;

import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
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
@Schema(name = "Order", description = "Full info about order")
public class OrderDto {
    @Schema(description = "Order id",
            required = true)
    Integer id;
    @Schema(description = "Total price of order",
            required = true)
    BigDecimal totalPrice;

    @Schema(description = "Payment type of order")
    PaymentType paymentType;
    @Schema(description = "Billing status of order",
            required = true)
    BillStatus billStatus;
    @Schema(description = "Status of order",
            required = true)
    OrderStatus orderStatus;

    @Schema(description = "User info of order",
            required = true)
    UserInfoDto user;
    @Schema(description = "Receiving info of order")
    ReceivingInfoDto receiving;
    @Schema(description = "Selected products that contains in order",
            required = true)
    List<SelectedProductInfoDto> selectedProducts;
}
