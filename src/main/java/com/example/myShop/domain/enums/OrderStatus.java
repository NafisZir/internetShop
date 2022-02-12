package com.example.myShop.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author nafis
 * @since 28.12.2021
 */

public enum OrderStatus {
    PENDING("Pending"),
    IN_STOCK("In stock"),
    ASSEMBLY("Assembly"),
    CREATING("Creating"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    IN_TRANSIT("In transit"),
    AWAITING_PICKUP("Awaiting pickup"),
    AWAITING_PAYMENT("Awaiting payment");

    private final String status;

    OrderStatus(String name) {
        this.status = name;
    }

    @JsonCreator
    public static OrderStatus decode(final String status) {
        return Stream.of(OrderStatus.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
