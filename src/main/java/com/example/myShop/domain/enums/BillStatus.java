package com.example.myShop.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Getter
@RequiredArgsConstructor
public enum BillStatus {
    AWAITING_PAYMENT(1),
    COMPLETED(2);

    private final int Number;
}
