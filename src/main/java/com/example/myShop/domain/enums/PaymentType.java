package com.example.myShop.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author nafis
 * @since 09.02.2022
 */

public enum PaymentType {
    CASH("Cash"),
    BANK_CARD_OFFLINE("Bank card offline"),
    BANK_CARD_ONLINE("Bank card online");

    private final String payment;

    PaymentType(String payment) {
        this.payment = payment;
    }

    @JsonCreator
    public static PaymentType decode(final String payment) {
        return Stream.of(PaymentType.values()).filter(targetEnum -> targetEnum.payment.equals(payment)).findFirst().orElse(null);
    }

    @JsonValue
    public String getPayment() {
        return payment;
    }
}
