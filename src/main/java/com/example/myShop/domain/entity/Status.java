package com.example.myShop.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author nafis
 * @since 28.12.2021
 */

public enum Status {
    PENDING("Pending"),
    IN_STOCK("In stock"),
    ASSEMBLY("Assembly"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    IN_TRANSIT("In transit"),
    AWAITING_PICKUP("Awaiting pickup"),
    AWAITING_PAYMENT("Awaiting payment");

    private String status;

    Status(String name) {
        this.status = name;
    }

    @JsonCreator
    public static Status decode(final String status) {
        return Stream.of(Status.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
