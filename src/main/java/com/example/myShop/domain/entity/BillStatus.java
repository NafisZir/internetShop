package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author nafis
 * @since 09.02.2022
 */
public enum BillStatus {
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    AWAITING_PAYMENT("Awaiting payment");

    private final String status;

    BillStatus(String name) {
        this.status = name;
    }

    @JsonCreator
    public static BillStatus decode(final String status) {
        return Stream.of(BillStatus.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
