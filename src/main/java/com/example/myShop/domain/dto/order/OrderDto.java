package com.example.myShop.domain.dto.order;

import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.user.UserInfoDto;
import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.domain.enums.OrderStatus;
import com.example.myShop.domain.enums.PaymentType;
import com.example.myShop.domain.entity.SelectedProduct;
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
public class OrderDto {
    Integer id;
    BigDecimal price;

    PaymentType paymentType;
    BillStatus billStatus;
    OrderStatus orderStatus;

    UserInfoDto user;
    ReceivingInfoDto receiving;
    List<SelectedProduct> selectedProducts;
}
